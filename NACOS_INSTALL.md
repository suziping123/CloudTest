# Nacos 配置中心安装指南

## 📌 版本要求

**必须使用 Nacos 2.4.3 版本**（与项目兼容）

- 项目使用的 Spring Cloud Alibaba 版本：`2023.0.3.2`
- 对应的 Nacos Server 版本：**2.4.3**

---

## 📥 下载 Nacos 2.4.3

### 方式 1：从 GitHub 下载（推荐）

访问官方 Release 页面：
```
https://github.com/alibaba/nacos/releases/tag/2.4.3
```

下载文件：
- **Windows**: `nacos-server-2.4.3.zip`
- **Linux/Mac**: `nacos-server-2.4.3.tar.gz`

### 方式 2：使用命令行下载

#### Windows (PowerShell)
```powershell
# 下载
Invoke-WebRequest -Uri "https://github.com/alibaba/nacos/releases/download/2.4.3/nacos-server-2.4.3.zip" -OutFile "nacos-server-2.4.3.zip"

# 解压
Expand-Archive -Path "nacos-server-2.4.3.zip" -DestinationPath "."
```

#### Linux/Mac
```bash
# 下载
wget https://github.com/alibaba/nacos/releases/download/2.4.3/nacos-server-2.4.3.tar.gz

# 解压
tar -xzf nacos-server-2.4.3.tar.gz
```

---

## 🚀 启动 Nacos

### Windows 启动

```cmd
# 进入 nacos 目录
cd nacos

# 单机模式启动（开发环境）
bin\startup.cmd -m standalone
```

### Linux/Mac 启动

```bash
# 进入 nacos 目录
cd nacos

# 赋予执行权限
chmod +x bin/startup.sh

# 单机模式启动（开发环境）
bin/startup.sh -m standalone
```

---

## ✅ 验证启动

启动成功后，访问控制台：
```
http://localhost:8848/nacos
```

默认登录账号：
- 用户名：`nacos`
- 密码：`nacos`

---

## ⚙️ 项目配置

项目的微服务会自动连接到 Nacos，配置文件位于：

### service-order 配置
位置：`services/service-order/src/main/resources/application.yml`

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
      config:
        server-addr: localhost:8848
        file-extension: properties
```

### service-product 配置
位置：`services/service-product/src/main/resources/application.yml`

（类似配置）

---

## 🔧 常见问题

### 1. 端口冲突

如果 8848 端口被占用，修改 `conf/application.properties`：
```properties
server.port=8849
```

### 2. 内存不足

编辑启动脚本，调整 JVM 参数：

**Windows** (`bin/startup.cmd`):
```cmd
set "CUSTOM_NACOS_MEMORY=-Xms512m -Xmx512m -Xmn256m"
```

**Linux** (`bin/startup.sh`):
```bash
JAVA_OPT="${JAVA_OPT} -Xms512m -Xmx512m -Xmn256m"
```

### 3. 集群模式 vs 单机模式

- **开发环境**：使用 `-m standalone`（单机模式）
- **生产环境**：使用集群模式（需要配置 `conf/cluster.conf`）

---

## 📝 注意事项

1. ⚠️ **不要将 Nacos 二进制文件提交到 Git**
   - `nacos/target/` 目录已在 `.gitignore` 中忽略
   - 每个开发者自行下载安装

2. 📦 **必须使用 2.4.3 版本**
   - 不同版本可能存在兼容性问题
   - 项目依赖的 Spring Cloud Alibaba 2023.0.3.2 对应 Nacos 2.4.3

3. 🔐 **生产环境请修改默认密码**
   - 登录控制台后修改管理员密码
   - 启用认证功能：`nacos.core.auth.enabled=true`

---

## 🔗 相关链接

- Nacos 官网：https://nacos.io
- GitHub 仓库：https://github.com/alibaba/nacos
- 官方文档：https://nacos.io/docs/latest/what-is-nacos/
- 版本发布页：https://github.com/alibaba/nacos/releases
