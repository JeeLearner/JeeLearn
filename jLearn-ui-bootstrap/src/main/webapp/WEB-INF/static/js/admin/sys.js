$.sys = {
	user : {
		initOnlineListButton : function(){
			$(".btn-force-logout").click(function(){
				var checkbox = $.table.getAllSelectedCheckbox($(".table"));
				if(!checkbox.length){
					return;
				}
				$.app.confirm({
					message : "确认强制退出吗？",
					ok : function(){
						var url = ctx + "/admin/sys/user/online/forceLogout?" + checkbox.serialize();
						window.location.href = url;
					}
				});
			});
		}
	},
		
};