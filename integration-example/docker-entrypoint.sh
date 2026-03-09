#!/bin/sh

user=appuser

# 帮助信息
print_help() {
  echo "Usage: docker run [OPTIONS] <image> [APP_ARGS]"
  echo ""
  echo "Environment Variables:"
  echo "  JAVA_OPTS     Pass JVM arguments (e.g., '-Xmx512m -XX:+UseG1GC')."
  echo "                Do NOT pass JVM args as command line arguments."
  echo ""
  echo "Arguments:"
  echo "  First arg:    Jar file name (default: app.jar)"
  echo "  Rest args:    Application arguments (passed to main method)"
  echo ""
  echo "Examples:"
  echo "  docker run -e JAVA_OPTS='-Xmx512m' my-image"
  echo "  docker run my-image app.jar --server.port=9090"
}

# 如果第一个参数是 help 或 -h，打印帮助并退出
if [ "$1" = "help" ] || [ "$1" = "-h" ]; then
  print_help
  exit 0
fi


# 切换到指定用户重新执行 shell
if [ "$(id -u)" -eq 0 ]; then
  echo "change user to $user"
  exec gosu "$user" "$0" "$@"
fi


echo "当前时间: $(date +"%Y-%m-%d %H:%M:%S") 使用用戶: $(whoami) 运行 docker-entrypoint.sh ..."

# 解析参数
# jar包名称 默认值是app.jar
JAR_FILE="${1:-app.jar}"

# 获取额外的应用参数
# 跳过第一个参数 第一个参数当作jar名称
# - JAVA_OPTS环境变量认为设置了全部的jvm参数
# - $@认为包含 jar名称 参数1 参数2 参数3等等
shift
APP_ARGS="$@"

echo "完整启动命令: java $JAVA_OPTS -jar $JAR_FILE $APP_ARGS"
exec java $JAVA_OPTS -jar $JAR_FILE $APP_ARGS

