"""Git churn analysis via PyDriller (Git tool for Java 11 microservices)."""
import json
from collections import defaultdict
from pathlib import Path
from pydriller import Repository

ROOT = Path(__file__).resolve().parents[2]

def main(max_commits=200):
    churn = defaultdict(lambda: {"commits": 0, "added": 0, "removed": 0})
    commits_seen = 0
    for commit in Repository(str(ROOT)).traverse_commits():
        commits_seen += 1
        for mod in commit.modified_files:
            path = mod.new_path or mod.old_path
            if not path:
                continue
            path = path.replace("\\", "/")
            churn[path]["commits"] += 1
            churn[path]["added"] += mod.added_lines
            churn[path]["removed"] += mod.deleted_lines
        if commits_seen >= max_commits:
            break
    top = sorted(churn.items(), key=lambda kv: kv[1]["commits"], reverse=True)[:10]
    print(json.dumps({
        "commits_analyzed": commits_seen,
        "files_touched": len(churn),
        "top_churned_files": [{"path": p, **s} for p, s in top],
    }, indent=2))

if __name__ == "__main__":
    main()