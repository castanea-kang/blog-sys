layui.use(['table','laypage', 'layer', 'form', 'pagesize'], function(){
    var table = layui.table;
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage;
    var laypageId = 'pageNav';
    initilData(1, 10);

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
                var data  = result.result.list;
                layer.close(index);
                var pages = result.result.pages;
                var total = result.result.total;
                table.render({
                    elem: '#test'
                    ,data:data
                    ,toolbar:'#toolbarDemo'
                    ,title:'博客列表'
                    ,cols: [[
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'id', title:'序号', width:80, fixed: 'left', unresize: true, sort: true}
                        ,{field:'title', title:'标题', width:200}
                        ,{field:'typeStr', title:'类别', width:120}
                        ,{field:'keyword', title:'关键字', width:150}
                        ,{field:'clickNum', title:'浏览量', width:80}
                        ,{field:'blogName', title:'博主', width:150}
                        ,{field:'statusStr', title:'状态', width:80}
                        ,{field:'addTimeStr', title:'发表时间', width:160}
                        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:160}
                    ]]
                    ,page: false
                });
               // 头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            var data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                    };
                });

                //监听行工具事件
                table.on('tool(test)', function(obj){
                    var data = obj.data;
                    //console.log(obj)
                    var blogId = data.id;
                    if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            // obj.del();
                            $.ajax({
                                type: 'POST',
                                url: '/blog/deleteBlog',
                                async:false,
                                contentType: "application/json",
                                data: JSON.stringify({
                                    blogId:blogId
                                }),
                                dataType: 'json',
                                success: function(data){
                                    if(data.success){
                                        obj.del();
                                        layer.msg('已删除!',{icon:1,time:3000});
                                    }
                                }
                            });
                            layer.close(index);
                        });
                    } else if(obj.event === 'edit'){
                        layer.prompt({
                            formType: 2
                            ,value: data.email
                        }, function(value, index){
                            obj.update({
                                email: value
                            });
                            layer.close(index);
                        });
                    }
                });
                $('#dataConsole,#dataList').attr('style', 'display:block'); //显示FiledBox
                laypage.render({
                    elem:laypageId,
                    curr:currentIndex,
                    count:total,
                    pages:pages,
                    skip:true,
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
});
