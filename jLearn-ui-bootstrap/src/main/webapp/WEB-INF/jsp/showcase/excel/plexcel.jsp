<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader />
<div class="panel">
		<div
			class="paddt-40 paddb-40 widget-body bordered-left bordered-blueberry z-isoroll z-isoheight">
			 <div class="submit-file">
			 
				<form action="${ctx}/showcase/excel/readExcel"
					method="post"
					enctype="multipart/form-data" id="uploadInfor"
					onSubmit="return validateuploadInforFile(this);">
					<div style='width: 500px;'>
						<a href="javascript:;" class="a-upload add-file"> 
							<input type="file" name="uploadExcel" id="uploadExcel" style="margin-bottom:10px;"onchange="$('#uploadp').html($(this).val())" >浏览
						</a>
						<p id='uploadp' style="height: 20px;line-height: 28px;overflow: hidden;display: inline-block;"></p>				
					</div>		
					<div style="margin-top: 5px;width: 500px;">
						<a href="javascript:;" class="a-upload submit"style='margin-left:0px;'> 
							<input type="submit" value="解析" id="sub_excel"/>解析
						</a>
						<p id='suploadp' style="height: 20px;line-height: 28px;overflow: hidden;display: inline-block;"></p>				
					</div>		
					
					</br>
				</form>
			</div> 
			<div id="excelUploadMsg"></div>
			<div>
				<a class="btn btn-primary" id='btyes'>确认保存</a>
			</div>
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

function showResponse(responseText){
  $("#excelUploadMsg").empty();
  $("#excelUploadMsg").append(responseText.msg);
  
}

function validateExcelUpLoadFile(form) {
    var fileName = form.uploadExcel.value;
    
    if (fileName != "" ) {
        var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)).toLowerCase();
        var suppotFile = ["xls", "XLS", "xlsx", "XLSX"];
        for (var i = 0; i < suppotFile.length; i++) {
            if (suppotFile[i] == fileType) {
                return true;
            } else {
                continue;
            }
        }
        $('#suploadp').html("文件格式不正确！");
        return false;
    } else {
    	 $('#suploadp').html("请选择你需要的导入的文件");
        return false;
    }
}


/* $("#patch_Excel").click(function() {
	$.ajax({
	url: ctx+"/modules/fos/equipmanage/patchExcel",
	type:"POST"
})
}); */
$('#btyes').on('click',function(){
	if( $('#suploadp').html()=="解析Excel文件成功，请确认导入！"){
		location.href='${ctx}/showcase/excel/patchExcel';
	}else{
		alert('请选择需要添加的Excel文件，并解析。')
	}
})
</script>
<es:contentFooter />
