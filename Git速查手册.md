# Git 全流程与方法速查手册

> 涵盖单人开发、多人协作、翻车补救、分支策略等全部常用场景。

---

## 一、Git 核心三区

```
工作区 (Working)      暂存区 (Staging)       本地仓库 (Local)        远程仓库 (Remote)
   │                        │                       │                       │
   │   git add              │   git commit          │   git push            │
   │ ◄────────────────────► │ ◄───────────────────► │ ◄───────────────────► │
   │      git restore       │    git reset          │   git fetch / pull    │
```

- **工作区**：你正在编辑的文件
- **暂存区**：准备提交的快照
- **本地仓库**：已提交的历史记录
- **远程仓库**：GitHub / GitLab 等托管平台

---

## 二、单人开发流程（最常用）

```
git init  →  git add  →  git commit  →  git push
   │             │            │              │
   ▼             ▼            ▼              ▼
 空仓库        暂存区       本地提交       远程仓库
                                              │
                                 git clone / git pull
                                              │
                                              ▼
                                         Linux 工作机
```

| 命令 | 作用 |
|------|------|
| `git init` | 初始化本地仓库 |
| `git add .` | 把所有修改加入暂存区 |
| `git add <file>` | 把指定文件加入暂存区 |
| `git commit -m "msg"` | 提交到本地仓库 |
| `git push origin main` | 推送到远程仓库 |
| `git clone <url>` | 从远程克隆到本地 |
| `git pull` | 拉取远程更新（= fetch + merge） |

---

## 三、分支管理（多人协作核心）

```
main ───●────●────●────●────●────●  (稳定发布线)
              \            /
feature-a      ●────●────●        (开发分支，完成后合并)
```

### 常用命令

| 命令 | 作用 |
|------|------|
| `git branch` | 查看本地分支列表 |
| `git branch -a` | 查看所有分支（含远程） |
| `git branch <name>` | 创建分支（不切换） |
| `git checkout -b <name>` | 创建并切换到新分支 |
| `git switch <name>` | 切换到已有分支（新版推荐） |
| `git merge <name>` | 把 `<name>` 分支合并到当前分支 |
| `git branch -d <name>` | 删除已合并的本地分支 |
| `git branch -D <name>` | 强制删除本地分支 |
| `git push origin --delete <name>` | 删除远程分支 |

### 典型分支工作流

```bash
# 1. 从 main 切出功能分支
git checkout -b feature-login

# 2. 写代码...

# 3. 提交
git add .
git commit -m "feat: 完成登录模块"

# 4. 推到远程提 PR
git push origin feature-login

# 5. 合并回主线
git checkout main
git merge feature-login

# 6. 清理本地分支
git branch -d feature-login
```

---

## 四、撤销操作（翻车补救）

```
翻车程度由浅到深 ──────────────────────────────────────────►

还没 add              已 add 未 commit          已 commit 未 push         已 push
git restore <file>    git restore --staged     git reset --soft HEAD~1   git revert <commit>
                       <file>                   (回到commit前)            (生成反向提交)
                       git reset --hard HEAD    git reset --mixed HEAD~1  ⚠️ 已推送慎用 reset
                                                (回到add前)
```

### 具体场景

| 场景 | 命令 | 说明 |
|------|------|------|
| 改坏了，想回到上次 commit 的状态 | `git restore <file>` | 只丢弃工作区改动 |
| `git add` 多了，想撤回暂存 | `git restore --staged <file>` | 回到 add 前 |
| commit 写错了，想重新提交 | `git commit --amend` | 覆盖上一次 commit |
| 回退到某个历史 commit | `git reset --hard <hash>` | 彻底回到过去 |
| 撤销某次已推送的 commit | `git revert <hash>` | 生成新 commit，安全 |
| 找回被误删的 commit | `git reflog` → `git checkout <hash>` | 后悔药 |

---

## 五、储藏（临时切换分支急救包）

```
正在 feature-A 开发中，突然要修 main 的紧急 bug：

git stash              ┌──────────┐
   │                   │ stash@{0}│ ← 工作区快照存到这里
   ▼                   └──────────┘
git checkout main      → 切到 main 修 bug → commit → push
git checkout feature-A → 切回来
git stash pop          → 恢复之前的工作状态
```

| 命令 | 作用 |
|------|------|
| `git stash` | 暂存当前工作区 |
| `git stash list` | 查看储藏列表 |
| `git stash pop` | 恢复最近一次储藏并删除记录 |
| `git stash apply` | 恢复但不删除 |
| `git stash drop` | 删除某个储藏 |
| `git stash clear` | 清空所有储藏 |

---

## 六、查看历史（排查问题必备）

| 命令 | 作用 |
|------|------|
| `git log --oneline` | 一行一个 commit |
| `git log --graph --all --oneline` | 分支可视化图 |
| `git log -p` | 每次 commit 的具体改动 |
| `git diff` | 工作区 vs 暂存区 |
| `git diff --staged` | 暂存区 vs 最新 commit |
| `git diff main..feature` | 两个分支的差异 |
| `git blame <file>` | 看每一行是谁写的 |
| `git show <hash>` | 看某次 commit 的详情 |

### `git log --graph --all --oneline` 效果示意

```
*   a1b2c3d merge feature-login (main)
|\
| * d4e5f6g 完成登录模块
| * h7i8j9k 添加登录接口
|/
*   k0l1m2n 初始化项目
```

---

## 七、远程协作流程

```
┌────────────────────────────────────────────────────────────────┐
│                      你的 Windows 开发机                        │
│                                                                │
│   git init → git add . → git commit → git push                │
│                                          │                     │
└──────────────────────────────────────────┼─────────────────────┘
                                           │
                                           ▼
                              ┌───────────────────────┐
                              │   GitHub 远程仓库      │
                              │ suziping123/CloudTest │
                              └───────────┬───────────┘
                                          │
                          git clone       │       git clone
                              │           │           │
                              ▼           ▼           ▼
                    ┌──────────────┐  ┌──────────────┐
                    │  Linux 工作机  │  │  其他协作者   │
                    └──────────────┘  └──────────────┘
```

### 远程同步命令

| 命令 | 作用 |
|------|------|
| `git fetch` | 只下载远程更新，不合并 |
| `git pull` | 下载并合并（= fetch + merge） |
| `git pull --rebase` | 下载并变基（提交历史更干净） |
| `git remote -v` | 查看远程仓库地址 |
| `git remote add <name> <url>` | 添加远程仓库 |
| `git remote remove <name>` | 移除远程仓库 |

---

## 八、标签管理（发版标记）

| 命令 | 作用 |
|------|------|
| `git tag` | 列出所有标签 |
| `git tag v1.0.0` | 在当前 commit 打轻量标签 |
| `git tag -a v1.0.0 -m "说明"` | 打附注标签 |
| `git push origin v1.0.0` | 推送单个标签 |
| `git push origin --tags` | 推送所有标签 |
| `git tag -d v1.0.0` | 删除本地标签 |

---

## 九、Merge vs Rebase

| | `git merge` | `git rebase` |
|------|-------------|--------------|
| **历史线** | 保留分叉，有合并节点 | 线性，无分叉 |
| **示意** | `main: ●─●─●──M` (合并节点) | `main: ●─●─●─●─●─●` |
| **适用** | 公共分支合并功能分支 | 功能分支同步 main 的更新 |
| **禁忌** | — | 已推送的分支不要 rebase |

```
merge（保留分支历史）:              rebase（线性历史）:

main: ●──●──●──────M               main: ●──●──●──●──●──●
         \       /
feature:   ●──●──●
```

---

## 十、进阶操作速查

| 场景 | 命令 |
|------|------|
| 把别的分支的一个 commit 拿过来 | `git cherry-pick <hash>` |
| 找回误删的 commit | `git reflog` → `git checkout <hash>` |
| 二分查找引入 bug 的 commit | `git bisect start` → `good` / `bad` |
| 忽略已跟踪文件的后续修改 | `git update-index --assume-unchanged <file>` |
| 关联多个远程仓库 | `git remote add upstream <url>` |

---

## 十一、提交信息规范（推荐）

```
feat:     新功能
fix:      修bug
docs:     文档
refactor: 重构
test:     测试
chore:    杂项（如 .gitignore 修改）
```

示例：

```bash
git commit -m "feat: 新增用户登录接口"
git commit -m "fix: 修复订单金额计算错误"
git commit -m "docs: 更新 README 部署说明"
git commit -m "refactor: 提取公共校验逻辑"
git commit -m "chore: 更新 .gitignore 排除日志文件"
```

---

## 十二、日常工作速查卡

```bash
# ===== Windows 开发 → 推送 =====
git add .
git commit -m "feat: 新增xxx功能"
git push origin main

# ===== Linux 机 → 同步 =====
git pull origin main

# ===== 新建功能分支 =====
git checkout -b feature-xxx
# ... 开发 ...
git add .
git commit -m "feat: xxx"
git push origin feature-xxx
# 到 GitHub 提 Pull Request

# ===== 翻车了 =====
git status                          # 先看看当前状态
git restore <file>                  # 丢弃文件修改
git restore --staged <file>         # 取消暂存
git reset --soft HEAD~1             # 撤销最近一次 commit（保留改动）
git reflog                          # 后悔药入口
```

---

> 导出日期：2026-05-16
> 项目：cloud-demo (Spring Cloud 微服务)
