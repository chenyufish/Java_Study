# 前言
故事的起因是这样的：某天夜里，我突发奇想的想搭建一个个人网站，通过搜索了解一定知识以后，想从简单的入手，于是就选择了[Halo](https://halo.run/)，结果第一步[环境搭建](https://docs.halo.run/getting-started/install/docker-compose/)就把我干碎了！
![在这里插入图片描述](https://img-blog.csdnimg.cn/cdd56a55c2704f06804bff515aec94bf.png)
# 1、Docker是什么？
我怎么知道？我就是一个纯纯小白。我点进了图片上的连接，进入了[Docker官网](https://docs.docker.com/engine/install/)，凭借我一窍不通的英语水平找到了这个：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f485f8e994ce4a06952cf59b8f80debd.png)
下载很顺利，安装也很顺利，结果启动给我难住了。启动页面一直在转圈，我只好询问万能的C友（CSDN上的朋友）。C友说要启动Docker Desktop要先打开Hyper-V，才能虚拟化。这个很简单，按一下window键直接搜索启用或关闭windows功能。
![在这里插入图片描述](https://img-blog.csdnimg.cn/7579901a8a174610b73e585f0d958c9b.png)
找到Hyper-V勾选，接着点适用于Linux的windows子系统，还有windows虚拟机监控平台，然后重启。。。。。。不出意外，还是不行。然后我把目光看向了Docker Toolbox。

# 2、Docker Toolbox的艰难之旅

## 2.1	Docker Toolbox的镜像与安装

很不幸，关于Docker Toolbox能遇到的事几乎都被我遭了个遍！
首先就是Docker Toolbox的下载，我以为还是在Docker官网就能找到，于是我靠着我的半吊子英语在官网找了半天都没任何收获，好在C友还是很强大的让我去[阿里的镜像网站](http://mirrors.aliyun.com/docker-toolbox/windows/docker-toolbox/)找到了Docker Toolbox。
![在这里插入图片描述](https://img-blog.csdnimg.cn/8de43eec0bd6453b9059a21d94f52839.png)

短暂的等待安装完成以后，桌面上就是这三个图标
![在这里插入图片描述](https://img-blog.csdnimg.cn/0efcd19787544eb589035e99878e8ba4.png)

## 2.2	怎么找不到bash.exe了？

问题马上就来！双击桌面快捷方式启动，好家伙，一点就是这个：
![在这里插入图片描述](https://img-blog.csdnimg.cn/aa81280811444cf59b8c38d0d9730661.png)
？？？发生什么事了？一来就是路径有问题？好在C友是万能的，原因是我之前就安装过Git了，导致Docker Toolbox安装的时候没找到Git的bash.exe。

解决方法：

右键点击桌面Docker Quickstart Terminal这个图标，点击属性，出现下面的图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/9e76613ef3ab4464817213cd84bba433.png)
点击目标进行修改，需要填写正确的 Git bash.exe文件位置来启动docker star.sh文件。我的git安装在D:\Git下，Docker Toolbox安装在C盘。所以我这里写的是：

```
D:\Git\bin\bash.exe –login -i “C:\Program Files\Docker Toolbox\start.sh”
```
修改完成以后Docker Quickstart Terminal就能启动了。


## 2.3	啊？Hyper-V没有关干净？
启动以后问又来了：
```
VBoxManage.exe: error: Raw-mode is unavailable courtesy of Hyper-V. (VERR_SUPDRV_NO_RAW_MODE_HYPER_V_ROOT)
VBoxManage.exe: error: Details: code E_FAIL (0x80004005), component ConsoleWrap, interface IConsole

```
？？？
C友又有小妙招：[Windows10家庭版安装Docker Toolbox后，再安装Hyper-v的血泪史以及解决方法](https://blog.csdn.net/xym222/article/details/107666290#:~:text=%E7%AC%AC%E4%B8%80%E6%AD%A5%20%EF%BC%9A%E5%85%B3%E9%97%ADHyper-V%E6%9C%8D%E5%8A%A1%EF%BC%8C%E5%8F%96%E6%B6%88%E5%8B%BE%E9%80%89%20%E7%AC%AC%E4%BA%8C%E6%AD%A5%20%EF%BC%9A%E4%BB%A5%E9%98%B2%E4%B8%87%E4%B8%80%EF%BC%8C%E5%86%8D%E6%AC%A1%E5%85%B3%E9%97%ADHyper-V%E6%9C%8D%E5%8A%A1%EF%BC%8C%E7%AE%A1%E7%90%86%E5%91%98%E6%A8%A1%E5%BC%8F%E6%89%93%E5%BC%80cmd%E7%AA%97%E5%8F%A3%EF%BC%9A%20%E6%89%A7%E8%A1%8C%E5%91%BD%E4%BB%A4%20bcdedit%20/set,hypervisorlaunchtype%20off%20%E7%AC%AC%E4%B8%89%E6%AD%A5%20%EF%BC%9A%E4%BF%AE%E6%94%B9%E4%BD%8D%E4%BA%8E%20C:Program%20FilesDocker%20Toolboxstart.sh%E7%9A%84%E6%96%87%E4%BB%B6%EF%BC%8C%E4%BB%A5%E7%AE%A1%E7%90%86%E5%91%98%E8%BA%AB%E4%BB%BD%E8%BF%90%E8%A1%8C%E7%9A%84%E7%BC%96%E8%BE%91%E5%99%A8%E4%B8%AD%E6%89%93%E5%BC%80%EF%BC%8C%E4%BF%AE%E6%94%B9%E4%B8%8B%E9%9D%A2%E8%BF%99%E4%B8%80%E8%A1%8C%EF%BC%9A)
跟着这位C友我又把Hyper-V关闭，在命令提示符里也加上

```
bcdedit /set hypervisorlaunchtype off
```

## 2.4	boot2docker怎么不见了？

忙活了好一阵搞完以后，我又信心满满的启动Docker，结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/6dece534362c4c89913cc371b31e9553.png)

？？？好好好，这么玩是吧？
我能怎么办？还是找C友！
又找到一篇文章[Docker安装问题3](https://blog.csdn.net/lililuni/article/details/83243062)
这里面和我遇到问题一样，我很果断的去github上下载了[boot2docker](https://github.com/boot2docker/boot2docker/)
然后放在了C:\Users\libin.docker\machine\cache.的地址下，
再次启动，我已经不抱任何希望了，果然，还有问题。。。。。

## 2.5	虚拟网卡没了？

```
VBoxManage.exe: error: Failed to create the host-only adapter
```
错误又有了。好好好，我再忍你最后一次，我最后找到这篇文章，然后安装了虚拟网卡。
[VBoxManage.exe: error](https://blog.csdn.net/github_38336924/article/details/106684453)

最后一次启动嗷！！！
![在这里插入图片描述](https://img-blog.csdnimg.cn/4964b2d5305c41068a7f01d58da16fff.png)

绷不住了，真的受不了了！！！
我心态炸了，短时间内是不会再想见到docker的！
我再卸载和重开的边缘徘徊，真的，一个小白只是想搭建一个网站而已，就在第一步搭建环境沉船了。在弥留之间（已经凌晨一点了，第二天还有早八），我又一次把问题提交给了搜索引擎，在知乎上我找到了这样一篇文章：[Docker Toolbox 在windows下的安装](https://zhuanlan.zhihu.com/p/151381702)

## 2.6	VBoxManage.exe error？重装VirtualBox！
![在这里插入图片描述](https://img-blog.csdnimg.cn/fec59d2f2c9e453c912a1ac45bf15d07.png)
这里面的描述简直和我一摸一样，我再次提起最后一次精神，要是再有意外，俺就不玩了！
我之间跑到[Oracle VM VirtualBox 的官网](https://www.virtualbox.org/wiki/Downloads)选择最新的版本
![在这里插入图片描述](https://img-blog.csdnimg.cn/4b0e1a030d8d42a299a66d08fcbb0a17.png)
把原先的卸载了以后，直接安装在Docker的文件夹下面，重启！
。。。。。。

## 2.6	小鲸鱼终于来了！
我的天！我的小鲸鱼终于出来了！
![在这里插入图片描述](https://img-blog.csdnimg.cn/1447a8a908b8477ebf5915a311f49c22.png)
还是不放心，我又打开了PowerShell，输入：

```
docker-machine ls
```
打开以后发现虚拟机成功的Running了，才心满意足的关掉了电脑。
![在这里插入图片描述](https://img-blog.csdnimg.cn/494fe3a3e04b4308b4e48c44eaa204d9.png)

更多命令详情请看这篇文章 ：[docker Toolbox使用](https://blog.csdn.net/yilvqingtai/article/details/114881131)

这里面有更多有关Docker的命令以及后续镜像储存位置的改变。

---

# 总结
要善于运用工具！
放弃的时候要想想再多试一次！也许成功就在下一次！
这是我第一次在CSDN上正儿八经的写文章，有很多技术上、排版上的问题都没有搞懂，哪里说的不好，哪里说的不对的地方请各位C友海涵。
