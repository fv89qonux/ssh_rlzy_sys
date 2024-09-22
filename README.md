## 本项目实现的最终作用是基于SSH公司企业人力资源管理系统
## 分为1个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 修改密码
 - 出差管理
 - 员工信息管理
 - 培训信息管理
 - 岗位管理
 - 工资信息管理
 - 打卡记录
 - 排班管理
 - 管理员登录
 - 部门信息管理
## 数据库设计如下：
# 数据库设计文档

**数据库名：** ssh_rlzy_sys

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [admin](#admin) | 管理员表 |
| [ssh_card](#ssh_card) |  |
| [ssh_ccpx](#ssh_ccpx) |  |
| [ssh_clock](#ssh_clock) |  |
| [ssh_department](#ssh_department) |  |
| [ssh_employee](#ssh_employee) |  |
| [ssh_post](#ssh_post) |  |
| [ssh_wages](#ssh_wages) |  |

**表名：** <a id="admin">admin</a>

**说明：** 管理员表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | username |   varchar   | 255 |   0    |    N     |  Y   |       | 用户名  |
|  2   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |

**表名：** <a id="ssh_card">ssh_card</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | CARDID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | EMPLOYEE |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | YKH |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | XKH |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | ZF |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_ccpx">ssh_ccpx</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | CCPXID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | EMPLOYEE |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | KSSJ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | CCPXNAME |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | JSSJ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | SX |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_clock">ssh_clock</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | CLOCKID |   varchar   | 255 |   0    |    N     |  Y   |       |   |
|  2   | EMPLOYEE |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | RIQI |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | SBDATE |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | XBDATE |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | CLOCKBZ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  7   | NYR |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_department">ssh_department</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | DEPARTMENTID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | DEPARTMENTNAME |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_employee">ssh_employee</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | EMPLOYEEID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | BIRTH |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | RZTIME |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | DEPARTMENT |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | POST |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | EMPLOYEEBZ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  9   | ZH |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_post">ssh_post</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | POSTID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | POSTNAME |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="ssh_wages">ssh_wages</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | WAGESID |   varchar   | 255 |   0    |    N     |  Y   |       |   |
|  2   | EMPLOYEE |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | FFND |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | FFYF |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | JBGZ |   float   | 13 |   0    |    Y     |  N   |   NULL    |   |
|  6   | QQJL |   float   | 13 |   0    |    Y     |  N   |   NULL    |   |
|  7   | GWJT |   float   | 13 |   0    |    Y     |  N   |   NULL    |   |
|  8   | GLGZ |   float   | 13 |   0    |    Y     |  N   |   NULL    |   |
|  9   | ZF |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

