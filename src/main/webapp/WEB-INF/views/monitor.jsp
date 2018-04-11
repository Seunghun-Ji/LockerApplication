<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title> 모니터링 </title>
     <script type ="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
     <script>
     var url_ccInfo = "http://172.31.37.117:5000/chain";
     var url_prefix = "http://172.31.37.117:5000/chain/blocks/";
     var blockNum = 1;// 블록번호
     var chianHeight;// 블록체인 높이
     var stop = false;
     //모니터링 개시
     function startMonitor() {
         stop = false;
         var table = document.getElementById('list');
         //체인정보 취득
         getChainInfo(function success(data) {
             chainHeight = data.height;
             for(var i = 1; i <= chainHeight; i++) {
                 //블록 정보 취득
                 getBlockinfo(data, table);
             }
         }, function error(data) {});
         //1초마다 다음 블록 해답 블록 정보를 취득
         setInterval(function(data, table) {
             watchBlock(data, table);
         }, 1000);
     }
     // 모니터 정지
     function stopWatch() {
         stop = true;
         console.log("stop");
     }
     //블록체인 정보 취득
     function getChainInfo(success, error) {
         url = url_ccInfo;
         $.ajax({
             type: 'get',
             url: url,
             contentType: 'application/JSON',
             dataType: 'JSON',
             scriptCharset: 'utf-8',
             success: function(data) {
                 success(data);
             },
             error: function(data) {
                 error(data);
             }
         });
     }
     //블록 정보 취득
     function getBlockinfo(data, table) {
         executeJsonRpc(function success(data) {
             //블록 정보가 존재하는 경우
                insertBlockRow(data, table);
         }, function error(data) {
             // 블록정보가 존재하지만 블록 내용이 빈 경우
                if (!data.responseText.match(/resource not found/)) {
                    insertErrBlockRow(table);
                }
         });
     }
     //블록 정보 편집
     function insertBlockRow(data, table) {
         var row = table.insertRow();
         var td = row.insertCell(0);
         td.innerHTML = blockNum++;
         var td = row.insertCell(1);
         var date = new Date(
         parseInt(data.transactions[0].timestamp.seconds) *1000);
         td.innerHTML = date.toString();
         var td = row.insertCell(2);
         td.innerHTML = data.previousBlockHash;
         var td = row.insertCell(3);
         td.innerHTML = data.transactions[0].nonce;
         var td = row.insertCell(4);
          td.innerHTML = "<input type ='text' value ='" + JSON.stringify(data.transactions) + "'/></td>";   
     }
     //에러시 블록 정보 편집
     function insertErrBlockRow(table) {
         var row = table.insertRow();
         var td = row.insertCell(0);
         td.innerHTML = blockNum++;
         var td = row.insertCell(1);
         td.innerHTML = "";
         var td = row.insertCell(2);
         td.innerHTML = ""
         var td = row.insertCell(3);
         td.innerHTML = ""
         var td = row.insertCell(4);
         td.innerHTML = ""
     }
     //블록 감시
     function watchBlock(data, table) {
         var table = document.getElementById('list');
         if (stop) {
             return;
         }
         getBlockinfo(data, table);

     }
     //JSON-RPC 실행
     function executeJsonRpc(success, error) {
         url = url_prefix + blockNum;
         $.ajax({
             async: false,
             type: 'get',
             url: url,
             contentType: 'application/JSON',
             dataType: 'JSON',
             scriptCharset: 'utf-8',
             success: function(data) {
                 success(data);
             },
             error: function(data) {
                 error(data);
             }
         });
     }
     </script>
</head>
<body>
    <br />
    <input type="button" value="start" onclick="startMonitor();" />
    <input type="button" value="stop" onclick="stopWatch();" />
    <table id="list" border="1">
        <tr>
            <th>Block<br />Number
            </th>
            <th>TimeStamp</th>
            <th>BlockHash</th>
            <th>Nonce</th>
            <th>Transaction</th>
        </tr>
    </table>
</body>
</html>