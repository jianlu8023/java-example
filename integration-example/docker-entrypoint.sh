#!/bin/sh

user=appuser


# 切换到指定用户重新执行 shell
if [ "$(id -u)" -eq 0 ]; then
  echo "change user to $user"
  exec gosu "$user" "$0" "$@"
fi


echo "当前时间: $(date +"%Y-%m-%d %H:%M:%S") 使用用戶: $(whoami) 运行 docker-entrypoint.sh ..."

exec java -jar "$@"
