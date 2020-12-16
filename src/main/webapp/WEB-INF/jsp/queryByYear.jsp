<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    </style>
    <link rel="stylesheet" href="/lay/css/layui.css" media="all">
</head>
<body>

<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <!--查询框-->
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">年份</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="number" id="begin" value="1000" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="number" id="end" value="2000" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">统计范围</label>
                            <div class="layui-input-inline">
                                <input type="number" id="range" value = "100" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    <!--查询按钮-->
                        <button type="button" class="layui-btn"  onclick="loadData()">查询</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body" style="height: 500px;">
                    <!--统计图表展示-->
                    <!--切换统计图-->
                    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                        <ul class="layui-tab-title">
                            <li class="layui-this">列表</li>
                            <li>折线图</li>
                            <li>柱状图</li>
                            <li>饼图</li>
                        </ul>
                        <div class="layui-tab-content" style="height: 100px;">
                            <div class="layui-tab-item layui-show">
                                <div class="tablediv" style="width: 100%;height: 300px">
                                    <table class="layui-hide" id="demo" lay-filter="test"></table>
                                </div>
                            </div>
                            <div class="layui-tab-item">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                                <div id="lineChart" style="width: 600px; height: 400px;"></div>
                            </div>
                            <div class="layui-tab-item">
                                <div id="barChart" style="width: 600px; height: 400px;"></div>
                            </div>
                            <div class="layui-tab-item">
                                <div id="pineChart" style="width: 600px; height: 400px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>


<!--ajax请求后台数据-->
<script src="/echarts/echarts.js"></script>
<script src="/webjars/jquery/3.5.1/jquery.js "></script>
<script src="/lay/layui.js"></script>
<script>
    //请求后端数据
    var users ;
    var tableIns;
    var jsonUsers;
    function loadData(){
        //创建表数据
        layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
            var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格

            //执行一个 table 实例
            tableIns =   table.render({
                elem: '#demo'
                ,height: 420
                ,title: '用户表'
                ,data:[]
                ,page: true //开启分页
                ,totalRow: false //开启合计行
                ,cols: [[ //表头
                    {field: 'id', title: 'ID', width:120, sort: true, fixed: 'left', totalRowText: '合计：'}
                    ,{field: 'sex', title: '性别', width:120,templet: function(d){if(d.sex == 0){return '男'}else{return '女'}}}
                    ,{field: 'birthYear', title: '出生年份', width: 120, sort: true, totalRow: true}
                    ,{field: 'mileage', title: '总旅行里程', width:120, sort: true}
                    ,{field: 'travelTime', title: '总旅行时间', width: 120, sort: true, totalRow: true}
                    ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
                ]]
            });
            //查询后台数据之后，刷新表数据
            $.post("${pageContext.request.contextPath}/ajax/queryByYear",{begin:$("#begin").val(), end:$("#end").val()},
                function (data,status) {
                    users = data;
                    tableIns.reload(
                        {
                            data:JSON.parse(users),
                            where: {}
                            ,page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        }
                    );
                }
            );
            //table分页
            laypage.render({
                elem: 'pageDemo' //分页容器的id
                ,count: 100 //总页数
                ,skin: '#1E9FFF' //自定义选中色值
                //,skip: true //开启跳页
                ,jump: function(obj, first){
                    if(!first){
                        layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                    }
                }
            });
        });

        //加载三种图

        var lineChart = echarts.init(document.getElementById('lineChart')); //折线图
        var barChart = echarts.init(document.getElementById('barChart')); //柱状图
        var pineChart = echarts.init(document.getElementById('pineChart'));  //饼图

        //请求后台数据
        $.post("${pageContext.request.contextPath}/ajax/queryChartByYear",{begin:$("#begin").val(), end:$("#end").val(),range:$("#range").val()},
            function (data,status) {
                jsonUsers = JSON.parse(data)
                var listkey=[];
                var listvalue=[] ;
                for (var  key in jsonUsers){
                    listkey.push(key*$("#range").val());
                    var name = ((key*$("#range").val())+"-"+((key+1)*$("#range").val())).toString();
                    listvalue.push({value:jsonUsers[key],name:(key*$("#range").val())});
                }
                //饼图
                optionPine = {
                    title: {
                        text: '旅行用户数目统计图',
                        subtext: '饼图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: listkey
                    },
                    series: [
                        {
                            name: '区间旅行游客数目',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: listvalue,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };

                //折线图
                var listkey=[];
                var listvalue=[] ;
                for (var  key in jsonUsers){
                    var key1 = key*$("#range").val();var key2 = parseInt(key1)+parseInt($("#range").val());
                    listkey.push(key1+'-'+key2);
                    listvalue.push(jsonUsers[key]);
                }
                optionLine = {
                    title: {
                        text: '旅行用户统计图',
                        left: 'center'
                    },
                    xAxis: {
                        name:'出生年',
                        type: 'category',
                        data: listkey
                    },
                    yAxis: {
                        name:'旅行用户数（人）',
                        type: 'value'
                    },
                    series: [{
                        data: listvalue,
                        type: 'line',
                    }]
                };

                //柱状图,使用的数据格式和折线图一样
                optionBar = {
                    xAxis: {
                        type: 'category',
                        data: listkey
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: listvalue,
                        type: 'bar',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(220, 220, 220, 0.8)'
                        }
                    }]
                };
                lineChart.setOption(optionLine);
                barChart.setOption(optionBar);
                pineChart.setOption(optionPine);
            }
        );
    }

    //页面加载完成之后自动请求一次数据
    $(document).ready(function () {
     loadData();
    });

</script>
</body>
</html>