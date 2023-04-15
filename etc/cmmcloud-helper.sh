#!/bin/bash

echo "======================================="
echo "        cmmcloud helper start..        "
echo "======================================="

cd ../src/main/java
cmmcloud=(`ls -a | grep 'cmmcloud'`)

if [ -z ${cmmcloud} ]; then
  echo "there is no cmmcloud directory.."
  echo "make cmmcloud.."

  mkdir cmmcloud
fi

cd cmmcloud
ls=(`ls -a | grep '.git'`)

if [ -z ${ls} ]; then
  echo "cmmcloud directory is not clone directory.."
  echo "remove cmmcloud.."

  cd ..
  rm -rf cmmcloud

  echo "start clone.."
  git clone https://github.com/BazerHanMinSu/cmmcloud.git

else
  echo "cmmcloud directory is clone directory.."
  echo "start pull.."

  git pull https://github.com/BazerHanMinSu/cmmcloud.git
fi

echo "end.."