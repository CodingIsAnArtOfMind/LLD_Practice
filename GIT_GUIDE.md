# Git Quick Reference

## First-Time Setup (New Repo)
```zsh
git init
git add .
git commit -m "Initial commit"

git remote add origin https://github.com/USERNAME/REPO.git
git branch -M main
git push -u origin main
```

## Daily Workflow
```zsh
git status --short
git add .
git commit -m "Your message"
git push
```

## Check Remote and Branch
```zsh
git remote -v
git branch --show-current
```

## Sync With Remote
```zsh
git pull
```

## Common Fixes
### Remove a file from staging
```zsh
git restore --staged path/to/file
```

### Change last commit message (not pushed)
```zsh
git commit --amend -m "New message"
```

### Add a missing file to last commit (not pushed)
```zsh
git add path/to/file
git commit --amend --no-edit
```

