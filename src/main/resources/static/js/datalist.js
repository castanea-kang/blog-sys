layui.use(['element','laypage', 'layer', 'form', 'pagesize'], function () {
	var element = layui.element;
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form
	var laypage = layui.laypage;
    var laypageId = 'pageNav';
	initilData(1, 10);
    /**
     * 初始化数据
     * currentIndex：当前也下标
     * pageSize：页容量（每页显示的条数）
     */
    function initilData(currentIndex, pageSize) {
        var index = layer.load(1);
        var status = $('#status').val();
        var typeId = $('#type').val();
        var keyword = $('#keyword').val();
        console.log(keyword);
        $.ajax({
     	    url:'/blog/getList',
          	async:false,
          	type:'post',
            contentType: "application/json",
          	dateType:'json',
          	data:JSON.stringify({
				typeId:typeId,
				status:status,
				keyword:keyword,
				pageNo:currentIndex,
				pageSize:pageSize
            }),
    		success:function(result){
    			// var obj = JSON.parse(result);
    			// var count = obj.count;
    			var data  = result.result.list;
    			layer.close(index);
	            //计算总页数（一般由后台返回）
	            // pages = Math.ceil(count / pageSize);
				var pages = result.result.pages;
                var total = result.result.total;
	            var html = '';
	            //遍历账单集合
	            for (var i = 0; i < data.length; i++) {
	                var item = data[i];
	                html += "<tr>";
	                html += "<td>" + (parseInt(result.result.pageSize)*(parseInt(result.result.pageNum)-1)+parseInt(i)+parseInt("1")) + "</td>";
	                html += "<td>" + item.title +"</td>";
	                html += "<td>" + item.typeStr + "</td>";
	                html += "<td>" + item.keyword + "</td>";
                    html += "<td>" + item.clickNum + "</td>";
	                html += "<td>" + item.blogName + "</td>";
	                html += "<td>" + item.statusStr + "</td>";
                    html += "<td>" + item.addTimeStr + "</td>";
                    html += "<td><a class='layui-btn layui-btn-xs' lay-event='detail'>查看</a><a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='del'><i class='layui-icon'></i>删除</a></td>";
	                html += "</tr>";
                    // var st = item.statusStr;
                    // if(st == "已发布"){
	                	// $('#p1').attr('class','layui-btn layui-btn-normal');
					// }else if(st == '草稿'){
                     //    $('#p1').attr('class','layui-btn');
					// }else{
                     //    $('#p1').attr('class','layui-btn layui-btn-disabled');
					// }
	            }
	            $('#dataContent').html(html);
	            $('#dataConsole,#dataList').attr('style', 'display:block'); //显示FiledBox
                laypage({
	                cont:laypageId,
	                curr:currentIndex,
	                count:total,
	                pages:pages,
	                groups:10,
	                skip:true,
	                curr:currentIndex,
	                jump:function (obj, first) {
	                    var currentIndex = obj.curr;
	                    if (!first) {
	                        initilData(currentIndex, pageSize);
	                    }
	                }
	            });
	            /**
	             * 设置每页数量
	             */
	            layui.pagesize(laypageId, pageSize).callback(function (newPageSize) {
	                initilData(1, newPageSize);
	            });
    		}
          });
    }
    form.on('submit(formSearch)',function (data) {
        initilData(1, 10);
        return false;
    });
    $('#reset').click(function () {
    	$('#status').val("");
        $('#type').val("");
        $('#keyword').val("");
        form.render();
        initilData(1, 10);
    });
    // exports('datalist', {});
});
