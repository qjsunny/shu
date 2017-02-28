<?php
//链接数据库
$servername = "114.215.84.38";
$username = "root";
$password = "qazxsw";
$dbname = "shu";
$islive = 0;

// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);
// 检测连接
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
} 



$verifyData = file_get_contents("php://input");
//$verifyData = "{\"action\":\"on_play\",\"client_id\":105,\"ip\":\"139.71.22.215\",\"vhost\":\"__defaultVhost__\",\"app\":\"live\",\"tcUrl\":\"rtmp://ip:1935/live?user=player&pwd=123\",\"pageUrl\":\"\"}";
$obj=json_decode($verifyData);

if ( $obj->action == "on_connect"){
    echo "0";
}
else if ( $obj->action == "on_close"){
    echo "0";
}
else if ( $obj->action == "on_publish"){
    $app = $obj->app;
    $stream = $obj->stream;
    //如果开启了直播间，就验证通过
    $sql = "SELECT islive FROM t_live_room WHERE app = '".$app.
    "' AND stream = '".$stream."'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // 输出每行数据
        while($row = $result->fetch_assoc()) {
            $islive = $row["islive"];
        }
    } else {//用户没有开启过直播间
        echo "error";
    }
    if($islive == 1){
        echo 0;
    }else{
        echo 1;
    }

    //$arr = parse_url($obj->tcUrl);
    //$arr_query = convertUrlQuery($arr['query']);
    //if ($arr_query["user"] == "pub" && $arr_query["pwd"] == "123") {
    //    echo "0";
    //}
    //else {
    //     //echo "1";
    //     echo "0";
    //}
}
else if ( $obj->action == "on_unpublish"){
    echo "0";
}
else if ( $obj->action == "on_play"){
    //$arr = parse_url($obj->tcUrl);
    //$arr_query = convertUrlQuery($arr['query']);
    //if ($arr_query["user"] == "player" && $arr_query["pwd"] == "123") {
    //    echo "0";
    //}
    //else {
    //     echo "1";
    //}
    echo "0";
}
else if ( $obj->action == "on_stop"){
    echo "0";
}
else if ( $obj->action == "on_dvr"){
    echo "0";
}
else{
    echo "1";
}
$conn->close();
function convertUrlQuery($query)
{
    $queryParts = explode('&', $query);
    $params = array();
    foreach ($queryParts as $param) {
        $item = explode('=', $param);
        $params[$item[0]] = $item[1];
    }
    return $params;
}
 
function getUrlQuery($array_query)
{
    $tmp = array();
    foreach($array_query as $k=>$param)
    {
        $tmp[] = $k.'='.$param;
    }
    $params = implode('&',$tmp);
    return $params;
}
?>