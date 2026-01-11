> Beta 5 还未更新完毕，目前已完成大约50%。

# Function Zombies Helper  
[English](README.md) | 简体中文(有更新日志)

一个为**函数僵尸末日**制作的辅助模组。

## 功能

- 血量显示
- Show Spawn Time
- 也许还有更多？emm...

请前往 [Modrinth](https://modrinth.com/mod/function-zombies-helper) 获取模组文件。

> 从 Beta 6 开始将不会继续在***此处***编写更新日志。

## 更新日志
### 1.0.0 Beta 5 (非最终版本)
1. 新增 贡献者名单。
2. 新增 **重构并重新加入了 /fzh 指令**。
3. 修改 替换了血量显示的渲染方式以**支持置顶显示**。
4. 修改 对主菜单的 "FZH Loaded" 进行本地化。
5. 修改 血量显示位置修改界面添加标题。
6. 修改 **将英语本地化工作迁移至 Crowdin**。
7. 修改 模组包名从 "com.yhsabc233.fzh" 更改为 "top.yhsabc233.fzh"。
8. 修改 **重制**了模组图标。
9. 优化 将血量显示位置修改界面的底部按钮位置调整至中间。
10. 优化 对大量代码进行优化。

2026年1月12日 12:30:00 （非最终更新时间）

本次更新参与者
- YHSabc233 [1. 2. 3. 4. 5. 6. 7. 8. 9. 10.]

### 1.0.0 Beta 4
#### 元旦快乐！
1. ***修改 FZH 配置修改可视化，但是原先的指令因为一些问题被全部移除（Beta 5 重构后会重新加入），但又新增 /fzhconfig***。
2. **修改 优化了血量显示的显示逻辑：如果血量显示的Y坐标位于*屏幕上半部分*，那么使用*从上到下排序*，否则使用*从下到上排序***。
3. 修改 完善本模组在 Mod Menu 中的信息显示。
4. 修改 为优化显示体验，从此版本开始将 valueBeforeName 配置项默认设置为 true。

2026年1月1日 20:30:00

本次更新参与者
- YHSabc233 [1. 2. 3. 4.]

### 1.0.0 Beta 3 RE
1. **现在模组可以通过[Mod Menu](https://modrinth.com/mod/modmenu/versions?g=1.21.5)模组自动检查更新了**。
2. SST（Show Spawn Time）功能测试Ver1：  
   信息显示功能（感觉没用 需自行输入/fzh beta ShowSpawnTime true开启）
3. 新增 扩展了帮助列表，新增配置项的帮助。 
4. 新增 /lang 指令，可快速切换语言并立即重载资源。（虽然不知道有没有用）
5. 修改 当玩家延迟显示为0ms将变为灰色。
6. 修改 部分英文翻译。 
7. 修改 配置项“displayMode”：
   “HEALTH”简化为“HP”，
   “DISTANCE”简化为“DIST”。
8. 修复 /fzh set position命令直接执行不会显示当前HPDP显示位置，反而/fzh set会触发，并且还无法正常显示数值。

2025年12月2日 21:08:56

本次更新参与者
- YHSabc233 [1. 2. 3. 4. 5. 7. 8.]
- XMM010 [6.]

### 1.0.0 Beta 2
1. 配置项“keepVIsibleWhenHudHidden”的名称简化为"alwaysDisplayed"。
2. 增加Mod Menu支持。
3. 模组名称移除YH'S前缀。

本次更新参与者
- YHSabc233 [2. 3.]
- XMM010 [1.]

### 1.0.0 Beta 1
1. 重构模组，现在不再需要Fabric Language Kotlin前置Mod。
2. “displayWhenHUDOff” 更名为 “keepVisibleWhenHudHidden”， 新增配置项：“textMargin” （文本上下间距），默认为10。 效果图：[图片] === 已知BUG（预计本周修复）：
3. position配置项只能自定义坐标，提供的四个预设项无法使用。

本次更新参与者
- YHSabc233 [1. 2. 3.]