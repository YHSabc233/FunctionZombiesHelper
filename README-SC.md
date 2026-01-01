<style>
    .avatar {
        width: 40px;
        height: 40px;
        border-radius: 50px;
        margin-right: 8px;
    }
    .date {
        text-align: right;
    }
</style>

# Function Zombies Helper  
[English](README.md) | 简体中文

一个为函数僵尸末日提供的辅助模组。

## 功能

- 血量显示
- Show Spawn Time
- 以及更多？emm...

前往 [Modrinth](https://modrinth.com/mod/function-zombies-helper) 获取更多信息。

## 更新日志
### 1.0.0 Beta 4
#### 元旦快乐！
1. ***修改 FZH 配置修改可视化，但是原先的指令因为一些问题被全部移除（Beta 5 重构后会重新加入），但又新增 /fzhconfig。***
<!--2. **修改 现在血量显示会根据玩家状态分颜色为：正常白色 倒地金色 死亡深红色 未知黑色。**-->
2. **修改 优化了血量显示的显示逻辑：如果血量显示的Y坐标位于*屏幕上半部分*，那么使用*从上到下排序*，否则使用*从下到上排序*。**
3. 修改 完善本模组在 Mod Menu 中的信息显示。
4. 修改 为优化显示体验，从此版本开始将 valueBeforeName 配置项默认设置为 true。

<p class="date">2026年1月1日 19:35:00</p>

本次更新参与者
- <img class="avatar" src="/img/head/YHSabc233.jpg">YHSabc233</img> [1. 2. 3. 4.]

### 1.0.0 Beta 3 RE
<!--1. 新增 配置项“displayWhen”：
            可选“ALWAYS”，“GAME_STARTED”。
             （用于调整血量显示功能在什么时候被启用，如果为“ALWAYS”，将全局保持显示不会中途隐藏，如果为“GAME_STARTED”，则只会在游戏开始后置顶显示并在结束后隐藏。）-->
1. **现在模组可以通过[Mod Menu](https://modrinth.com/mod/modmenu/versions?g=1.21.5)模组自动检查更新了。**
2. SST（Show Spawn Time）功能测试Ver1：  
   信息显示功能（感觉没用 需自行输入/fzh beta ShowSpawnTime true开启）
<!--传送门实验室（Portal Lab）第1-10回合SST测试-->
<!--1. 新增 FZH配置修改GUI **(需要安装[Mod Menu](https://modrinth.com/mod/modmenu/versions?g=1.21.5)模组)**-->
3. 新增 扩展了帮助列表，新增配置项的帮助。 
4. 新增 /lang 指令，可快速切换语言并立即重载资源。（虽然不知道有没有用）
4. 修改 当玩家延迟显示为0ms将变为灰色。
5. 修改 部分英文翻译。
6. 修改 配置项“displayMode”：
   “HEALTH”简化为“HP”，
   “DISTANCE”简化为“DIST”。
7. 修复 /fzh set position命令直接执行不会显示当前HPDP显示位置，反而/fzh set会触发，并且还无法正常显示数值。

<p class="date">2025年12月2日 21:08:56</p>

本次更新参与者
- <img class="avatar" src="/img/head/YHSabc233.jpg">YHSabc233</img> [1. 2. 3. 4. 5. 7. 8.]
- <img class="avatar" src="/img/head/XMM010.jpg">XMM010</img> [6.]

### 1.0.0 Beta 2
1. 配置项“keepVIsibleWhenHudHidden”的名称简化为"alwaysDisplayed"。
2. 增加Mod Menu支持。
3. 模组名称移除YH'S前缀。

本次更新参与者
- <img class="avatar" src="/img/head/YHSabc233.jpg">YHSabc233</img> [2. 3.]
- <img class="avatar" src="/img/head/XMM010.jpg">XMM010</img> [1.]

### 1.0.0 Beta 1
1. 重构模组，现在不再需要Fabric Language Kotlin前置Mod。
2. “displayWhenHUDOff” 更名为 “keepVisibleWhenHudHidden”， 新增配置项：“textMargin” （文本上下间距），默认为10。 效果图：[图片] === 已知BUG（预计本周修复）：
3. position配置项只能自定义坐标，提供的四个预设项无法使用。

本次更新参与者
- <img class="avatar" src="/img/head/YHSabc233.jpg">YHSabc233</img> [1. 2. 3.]