$(function(){
	//加载部门的数据
	getDept();
	//加载员工数据
	getLevel();
})

//将后台查询到的部门数据加载到前端页面
function getDept(){
	requestUrl = "/dept/findDept";
	$.when(ajaxRequest()).done(function(resultData){
		//在此处理业务
		//清空原有的数据
		$("#emp_add_dept").html("");
		//遍历显示查询到的数据
		$("#emp_add_dept").append("<option>=======请选择该员工所在的部门=======</option>");
		$.each(resultData.data, function(i,dept) {
			$("#emp_add_dept").append('<option value="' + dept.did + '">' + dept.dname + '</option>');
		});
	});
}

//将后台查询到的员工数据加载到前段数据
function getLevel(){
	requestUrl = "/level/findLevel";
	$.when(ajaxRequest()).done(function(resultData){
		//在此处理业务
		//清空原有的数据
		$("#emp_add_level").html("");
		//遍历显示查询到的数据
		$("#emp_add_level").append("<option>=======请选择该员工所在的部门=======</option>");
		$.each(resultData.data, function(i,level) {
			$("#emp_add_level").append('<option value="' + level.lid + '">' + level.title + '</option>');
		});
	});
}


function addEmp(){
	//使用插件进行数据的验证
	requestUrl = "/pages/addEmp";
	//验证通过，就完成表单的提交
	notie.confirm(
		"是否提交当前表单数据？？",
		'确认','取消',function(){
			//如果验证通过，就完成表单的提交！
			$("#addEmpForm")[0].submit();
	});
}
