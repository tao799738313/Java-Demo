# springCloud-shop

#### 项目提交到码云命令
git init

git remote add origin git@120.79.148.214:Visual-analysis/mytest.git  

git pull origin master // 把本地仓库的变化连接到远程仓库主分支
   
git remote rm origin  --删除建立的远程连接

git add .

git commit -m "test"

git push -u origin master

#### 项目设置忽略
git rm -r --cached .

git add .

git commit -m 'update .gitignore'

#### 如果本地使用代理
// 查看当前代理设置
git config --global http.proxy

// 设置当前代理为 http://127.0.0.1:1080 或 socket5://127.0.0.1:1080
git config --global http.proxy 'http://127.0.0.1:1080'
git config --global https.proxy 'http://127.0.0.1:1080'
git config --global http.proxy 'socks5://127.0.0.1:1080'
git config --global https.proxy 'socks5://127.0.0.1:1080' /

// 删除代理
git config --global --unset https.proxy
