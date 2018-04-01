<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader />
<div class="panel">
		<div
			class="paddt-40 paddb-40 widget-body bordered-left bordered-blueberry z-isoroll z-isoheight">
			 <div class="submit-file">
			 
				<form action="${ctx}/showcase/sample/patchExcel"
					method="post"
					enctype="multipart/form-data" id="uploadInfor"
					onSubmit="return validateuploadInforFile(this);">
					<div style='width: 500px;'>
						<a href="javascript:;" class="a-upload add-file"> 
							<input type="file" name="uploadExcel" id="uploadExcel" style="margin-bottom:10px;"onchange="$('#uploadp').html($(this).val())" >浏览
						</a>
					</div>		
					<div style="margin-top: 5px;width: 500px;">
							<input type="submit" value="上传" />上传
					</div>		
				</form>
			</div> 
			<!-- <div id="excelUploadMsg"></div>
			<div>
				<a class="btn btn-primary" id='btyes'>确认保存</a>
			</div> -->
		</div>
</div>


<script src="${ctx}/static/js/jquery.form.js"></script>

<script type="text/javascript">
var z_isohe = window.localStorage.getItem('iframehe');
$('.z-isoheight').css('height',z_isohe)
// 	zisoroll('.z-isoroll')
	$(window).resize(function() {
		var data = 80;
		isohe(data)
	});
/**
 * 上传excel
 * */
function validateuploadInforFile(form){
        if(!validateExcelUpLoadFile(form)) return false;
        var options = { 
            dataType: 'text',
            success:function(data){ 
            	$('#suploadp').html(data);
//                 var  listdata;
//             	for(var i in data.excelBeans){
//             		/* alert(data.excelBeans[i].order); */
//             		listdata =listdata+ "<tr><td>"+data.excelBeans[i].order+"</td><td>"+data.excelBeans[i].deviceName+"</td><td>"+data.excelBeans[i].deviceType+"</td><td>"+data.excelBeans[i].detailConfig+"</td><td>"+data.excelBeans[i].unit+"</td><td>"+data.excelBeans[i].count+"</td><td>"+data.excelBeans[i].serialNum+"</td></tr>";            		
//             	}
//             	$("#table tbody").html(listdata); 
            } 
        };
        $("#uploadInfor").ajaxSubmit(options);
        return false;
}
$('#btyes').on('click',function(){
		location.href='${ctx}/showcase/sample/patchExcel';
})

</script>
<es:contentFooter />
