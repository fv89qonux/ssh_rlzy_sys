var indexdata = 
[
    { text: '员工信息管理',isexpand:false, children: [ 
		{url:"Employee_AddView.action",text:"添加员工" },
		{url:"Employee_QueryEmployee.action",text:"员工信息管理"},
		{url:"Employee_zh.action",text:"员工账号管理"},
	]
    },
    { text: '部门信息管理', isexpand: false, children: [
		{ url: "Department_AddView.action", text: "添加部门信息" },
		{ url: "Department_QueryDepartment.action", text: "部门信息管理" },
	]
    }, 
	    { text: '岗位新增管理', isexpand: false, children: [
		{ url: "Post_AddView.action", text: "添加岗位" },
		{ url: "Post_QueryPost.action", text: "岗位新增管理" },
	]
    }, 
	    { text: '考勤信息管理', isexpand: false, children: [
		{ url: "Pb_pb.action", text: "员工排班" },
		{ url: "Clock_QueryClock.action", text: "打卡记录" },
		{ url: "Card_AddView.action", text: "发卡补卡添加" },
		{ url: "Card_QueryCard.action", text: "发卡补卡信息" },
	]
    }, 
	    { text: '出差培训信息管理', isexpand: false, children: [
		{ url: "Ccpx_QueryCcpx.action", text: "出差信息" },
		{ url: "Ccpx_QueryCcpxpx.action", text: "培训信息" },
	]
    }, 
    { text: '员工薪资管理', isexpand: false, children: [
	{ url: "Wages_Addcsh.action", text: "绩效考核" },
	{ url: "Wages_QueryWages.action", text: "工资信息" },
]
  }, 
	  { text: '系统管理', isexpand: false, children: [
			{ url: "password_modify.jsp", text: "修改密码" },
		]
	  }

];


var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" },
            { url: "dotnetdemos/grid/pager/server_scroll.aspx", text: "滚动分页" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" }, 
		    { url: "dotnetdemos/grid/treegrid/tree2.aspx", text: "树表格2" }
	    ]
        }
    ]
    }
];
