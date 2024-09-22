var indexdata = 
[

	    { text: '考勤信息管理', isexpand: false, children: [
		{ url: "Clock_AddView.action", text: "打卡" },
		{ url: "Clock_AddViewq.action", text: "请假调休" },
	]
    }, 
	  { text: '系统管理', isexpand: false, children: [
	  { url: "passwordC_modify.jsp", text: "修改密码" },
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
