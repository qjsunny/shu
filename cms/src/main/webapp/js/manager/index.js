/**
 * Created by james on 2017/4/8.
 */
$(function () {
//        $("#managerShow").click(function () {
//            var divshow = $("#show");
//            divshow.html("");
//            divshow.append("<tr>" +
//                "<th>id</th><th>username</th><th>level</th>" +
//                "<th>createUser</th><th>createTime</th>" +
//                "<th>updateTime</th><th>updateUser</th><th>isDelete</th>");
//            new Date();
//            $.get("/Manager/show", function (data) {
//                var arr = eval(data);
//                console.log(arr);
//                arr.forEach(function(manager) {
//                    divshow.append("<tr>" +
//                        "<td>"+ manager.id + "</td>" +
//                        "<td>"+ manager.username + "</td>" +
//                        "<td>"+ manager.level + "</td>" +
//                        "<td>"+ manager.createuser + "</td>" +
//                        "<td>"+ new Date(manager.createtime).toLocaleString() + "</td>" +
//                        "<td>"+ new Date(manager.updatetime).toLocaleString() + "</td>" +
//                        "<td>"+ manager.updateUser + "</td>" +
//                        "<td>"+ manager.isdelete + "</td>" +
//                        "</tr>");
//                })
//            });
//        });
    $("#managerShow").click(function () {
        $("#t_manager").html('<table id="t_managerShow" class="table table-striped table-bordered"></table>');
        $("#t_enterprise").html('<table id="t_enterpriseShow" class="table table-striped table-bordered"></table>');
        var tManager = $("#t_managerShow");
        tManager.append("<thead><tr>" +
            "<th>id</th><th>username</th><th>level</th>" +
            "<th>createUser</th><th>createTime</th>" +
            "<th>updateTime</th><th>updateUser</th><th>update</th>" +
            "</tr><thead>" +
            "<tbody></tbody>" +
            "<tfoot><tr>" +
            "<th>id</th><th>username</th><th>level</th>" +
            "<th>createUser</th><th>createTime</th>" +
            "<th>updateTime</th><th>updateUser</th><th>update</th>" +
            "</tr><tfoot>");
        var t = $('#t_managerShow').DataTable({
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            destroy: true,
//                retrieve: true,
            ajax: {
                url: "/Manager/show"
            },
            pageLength: 3,
            columns: [{
                "data": null
            }, {
                "data": "username"
            }, {
                "data": "level"
            }, {
                "data": "createuser"
            }, {
                "data": "createtime"
            }, {
                "data": "updatetime"
            }, {
                "data": "updateUser"
            }, {
                "data": "username"
            }],
            "columnDefs": [{
                "render": function(data) {
                    return new Date(data).toLocaleString()
                },
                "targets": [4, 5]
            },{
                "render": function (data) {
                    return "<input type='button' class='update btn-warning' value='更新' data-update='"+data+"'>&nbsp;&nbsp;&nbsp;" +
                        "<input type='button' class='delete btn-warning' value='删除' data-delete='"+data+"'>"
                    },
                "targets": [7]
            },{
                "searchable": false,
                "targets": [0, 2, 3, 4, 5, 6, 7]
            }],
            "dom": '<"toolbar">frtip'
        });

        t.on('draw.dt', function() {
            $(".delete").click(function() {
                var name = this.getAttribute("data-delete");
                //alert(this.getAttribute("data-delete"));
                $.ajax({
                    type: "get",
                    url: "/Manager/delete/"+name,
                    dataType: "json",
                    success: function(data) {
                        var temp = eval(data);
                        if (temp.status === "success") {
                            alert("删除"+name+"成功");
                            t.ajax.reload();
                        } else if (temp.status === "fail") {
                            alert("删除"+name+"失败");
                        }
                    }
                })
            });

            $(".update").click(function() {
                var name = this.getAttribute("data-update");
                window.location.href = "/Manager/updateIndex/"+name;
            })
        });

        $("div.toolbar").html("<a type='button' href='/Manager/addIndex'>" +
            "<input type='button' value='新增'></a>");

        t.on('order.dt search.dt',
            function() {
                t.column(0, {
                    "search": 'applied',
                    "order": 'applied'
                }).nodes().each(function(cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();
    });

    $("#enterpriseShow").click(function () {
        $("#t_manager").html('<table id="t_managerShow" class="table table-striped table-bordered"></table>');
        $("#t_enterprise").html('<table id="t_enterpriseShow" class="table table-striped table-bordered"></table>');
        var tEnterprise = $("#t_enterpriseShow");
        tEnterprise.append("<thead><tr>" +
            "<th>id</th><th>username</th><th>email</th><th>phoneNumber</th><th>address</th>" +
            "<th>certificate</th><th>money</th><th>advertisement</th>" +
            "<th>createTime</th><th>updateTime</th><th>updateUser</th>" +
            "</tr><thead>" +
            "<tbody></tbody>" +
            "<tfoot><tr>" +
            "<th>id</th><th>username</th><th>email</th><th>phoneNumber</th><th>address</th>" +
            "<th>certificate</th><th>money</th><th>advertisement</th>" +
            "<th>createTime</th><th>updateTime</th><th>updateUser</th>" +
            "</tr><tfoot>");
        var t = $('#t_enterpriseShow').DataTable({
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            destroy: true,
//                retrieve: true,
            ajax: {
                url: "/Enterprise/show"
            },
            pageLength: 3,
            columns: [{
                "data": null
            }, {
                "data": "username"
            }, {
                "data": "email"
            }, {
                "data": "phonenumber"
            }, {
                "data": "address"
            }, {
                "data": "certificate"
            }, {
                "data": "money"
            }, {
                "data": "advertisement"
            }, {
                "data": "createtime"
            }, {
                "data": "updatetime"
            }, {
                "data": "updateUser"
            }],
            "columnDefs": [{
                "render": function(data) {
                    return new Date(data).toLocaleString()
                },
                "targets": [8, 9]
            },{
                "searchable": false,
                "targets": [0, 2, 3, 4, 5, 6, 7, 8, 9, 10]
            }],
            "dom": '<"toolbar">frtip'
        });

        // $("div.toolbar").html("<a type='button' href='/Manager/addIndex'>" +
        //     "<input type='button' value='新增'></a>");

        t.on('order.dt search.dt',
            function() {
                t.column(0, {
                    "search": 'applied',
                    "order": 'applied'
                }).nodes().each(function(cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();
    });

    $("#enterpriseCertificate").click(function () {
        window.location.href = "/Enterprise/authentication/certificate/";
    });

    $("#enterpriseAdvertisement").click(function () {
        window.location.href = "/Enterprise/authentication/advertisement/";
    });

    $("#liveStreamClassify").click(function () {
        window.location.href = "/LiveStream/Classify";
    });

    $("#Distribution").click(function () {
        window.location.href = "/LiveStream/anchorDistribution";
    })

    $("#onlineNumber").click(function () {
        window.location.href = "/LiveStream/userOnlineNumber";
    })
});