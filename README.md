# Gopal.pan-framework
java general tools

#### 项目管理
* 源码版本号规则  
    - 主版本号：当功能模块有较大的变动,比如增加多个模块或者整体架构发生变化。此版本号由项目决定是否修改。  
    - 子版本号：当功能有一定的增加或变化,比如增加了对权限控制、增加自定义视图等功能。此版本号由项目决定是否修改。  
    - 阶段版本号：一般是 Bug 修复或是一些小的变动,要经常发布修订版,时间间隔不限,修复一个严重的bug即可发布一个修订版。此版本号由项目经理决定是否修改。  
    - 日期版本号:用于记录修改项目的当前日期,每天对项目的修改都需要更改日期版本号。此版本号由开发人员决定是否修改。  
    - 希腊字母版本号(Release):此版本号用于标注当前版本的软件处于哪个开发阶段,当软件进入到另一个阶段时需要修改此版本号。此版本号由项目决定是否修改。  
    
* 源码分支规则  
    - master: 主分支,主要用来版本发布。
    - develop：日常开发分支,该分支正常保存了开发的最新代码。
    - feature：具体的功能开发分支,只与 develop 分支交互。
    - release：release 分支可以认为是 master 分支的未测试版。比如说某一期的功能全部开发完成,那么就将 develop 分支合并到 release 分支,测试没有问题并且到了发布日期就合并到 master 分支,进行发布。
    - hotfix：线上 bug 修复分支。
  
  
  
#### git commit 提交规范
##### Commit message 都包括三个部分：Header,Body和Footer,其中 Body Footer 可以省略
```html
    <type>(<scope>): <subject>
    <BLANK LINE>
    <body>
    <BLANK LINE>
    <footer>
```

##### Header,Header部分只有一行,包括三个字段：type（必需）、scope（可选）和subject（必需）
> type（必需） type用于说明 commit 的类别
>> feat：新增功能  
>> fix：bug 修复  
>> docs：文档更新  
>> style：不影响程序逻辑的代码修改(修改空白字符,格式缩进,补全缺失的分号等,没有改变代码逻辑)  
>> refactor：重构代码(既没有新增功能,也没有修复 bug)  
>> perf：性能, 体验优化  
>> test：新增测试用例或是更新现有测试  
>> build：主要目的是修改项目构建系统(例如 glup,webpack,rollup 的配置等)的提交  
>> ci：主要目的是修改项目继续集成流程(例如 Travis,Jenkins,GitLab CI,Circle等)的提交  
>> chore：不属于以上类型的其他类,比如构建流程, 依赖管理  
>> revert：回滚某个更早之前的提交  
  
> scope（可选） scope用于说明 commit 影响的范围,比如数据层、控制层、视图层等等  
> subject（必需） subject是 commit 目的的简短描述
    
##### Body（可省） Body 部分是对本次 commit 的详细描述,可以分成多行
##### Footer（可省）
> 不兼容变动:如果当前代码与上一个版本不兼容,则Footer部分以BREAKING CHANGE开头,后面是对变动的描述、以及变动理由和迁移方法  
> 关闭 Issue, 如果当前 commit 针对某个issue,那么可以在 Footer 部分关闭这个 issue
   
#####  Revert

#### 编码约定
* 标准返回 
  - 返回使用标准的响应实体类 Response,文件下载类型除外
  - 当业务未处理成功时,返回对应的错误码和描述。当RCode定义的描述不能明确体现错误原因是可追加补充描述
  - 业务错误码区间为 50000 - 89999,其中一些号段为公共通用区间已被定义参见 RCode
  - 当系统发生未知未捕获的异常返回标准的 FW_UNKNOWN_ERROR(89999,"未知错误,请稍后重试或联系客服人员")
    
    
 