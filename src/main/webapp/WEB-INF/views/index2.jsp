<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "KO">
<head>
    <meta charset = "UTF-8">
    <title>COUNTER</title>
    <script type= "text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script>
        var url;
        var url_host = "http://172.31.37.117:5000/";
        var ccId;
        var counterList;
        var user_name;
        //로그인
        function login()
        {
            url = url_host + "registrar";
            user_name = $("#userName").val();
            var password = $("#password").val();
            var JSONdata = {
                "enrollId": user_name,
                "enrollSecret":password

            };
            executeJsonRpc(url, JSONdata,
            function success(data){
                //로그인 성공
                console.log("Login Success!!");

            },
            function error(data)
            {
                //로그인 실패
                console.log("login error");

            }
            );
        }
        //배포
        function deploy()
        {
            url = url_host + "chaincode";
            var JSONdata = createJSONdataForCounterApp("deploy", "init", [], 1);
            executeJsonRpc(url, JSONdata,
            function success(data)
            {
                //배포 성공
                ccId = data.result.message;
                console.log("Deploy Success!!");

            },
            function error(data)
            {
                // 배포 에러
                console.log("Deploy Error!!")

            }
            );
        }
        //갱신
        function refresh()
        {
            if (counterList != null)
            {
                var table = document.getElementById('list');
                for (var i = counterList.length; i>0; i--)
                {
                    table.deleteRow(i);
                }
            }
            // 카운터 정보 모두 취득
            getCounters();

        }
        // 카운터 증가
        function countup()
        {
            url = url_host + "chaincode";
            var targetIndex;
            var counterList = document.getElementsByName("counter");
            for(i=0; i< counterList.length;i++)
            {
                if (counterList[i].checked)
                {
                    //대상 카운터 정보 취득
                    targetIndex = counterList[i].value;
                }
            }
            // 대상자 카운트
            var JSONdata = createJSONdataForCounterApp("invoke", "countup", [targetIndex], 3);
            executeJsonRpc(url, JSONdata,
            function success(data)
            {
                console.log("countUp Success!!");

            },
            function error(data)
            {
                console.log("countUp error!!");
            }
            );

        }
        
        // 잠금 해제
        function Unlock()
        {
            url = url_host + "chaincode";
            var targetIndex;
            var counterList = document.getElementsByName("counter");
            for(i=0; i< counterList.length;i++)
            {
                if (counterList[i].checked)
                {
                    //대상 카운터 정보 취득
                    targetIndex = counterList[i].value;
                }
            }
            // 대상자 카운트
            var JSONdata = createJSONdataForCounterApp("invoke", "Unlock", [targetIndex], 7);
            executeJsonRpc(url, JSONdata,
            function success(data)
            {
                console.log("Unlock Success!!");

            },
            function error(data)
            {
                console.log("Unlock error!!");
            }
            );

        }
        
        // 잠금
        function Lock()
        {
            url = url_host + "chaincode";
            var targetIndex;
            var counterList = document.getElementsByName("counter");
            for(i=0; i< counterList.length;i++)
            {
                if (counterList[i].checked)
                {
                    //대상 카운터 정보 취득
                    targetIndex = counterList[i].value;
                }
            }
            // 대상자 카운트
            var JSONdata = createJSONdataForCounterApp("invoke", "Lock", [targetIndex], 9);
            executeJsonRpc(url, JSONdata,
            function success(data)
            {
                console.log("Lock Success!!");

            },
            function error(data)
            {
                console.log("Lock error!!");
            }
            );

        }
        
        
        //카운터 취득
        function getCounters()
        {
            url = url_host + "chaincode";
            var JSONdata = createJSONdataForCounterApp("query", "refresh", [], 5);
            executeJsonRpc(url, JSONdata,
                function success(data)
                {                    
                console.log(data);
                counterList = JSON.parse(data.result.message);
                var table = document.getElementById('list');
                for(var i =0; i<counterList.length; i++)
                {
                    //카운터 정보 취득
                    var counter = counterList[i];
                    console.log("["+i+"]", counter);
                    //### HTML 편집 table 행 추가, 편집 시작###
                    var row = table.insertRow();
                    var td = row.insertCell(0);
                    var radioButton1 = document.createElement('input');
                    radioButton1.type = 'radio';
                    radioButton1.name = 'counter';
                    radioButton1.value = i;
                    td.appendChild(radioButton1);
                    td = row.insertCell(1);
                    td.innerHTML = counter.name;
                    td = row.insertCell(2);
                    td.innerHTML = counter.lock;
                    //###HTML 편집 table 행 추가, 편집 끝###
                

                }
            },
            function error(data)
            {
                console.log("refresh error");
            }
            );
        }
        //JSON 메세지 생성
        function createJSONdataForCounterApp(method, functionName, args, id)
        {
            var JSONdata = {
                jsonrpc: "2.0",
                method: method,
                params: {
                    type: 1,
                    ctorMsg: {
                        function: functionName,
                        args: args
                    },
                    secureContext: user_name,
                },
                id: id
            };
            //체인 코드 ID 설정
            if (functionName == "init")
            {
                JSONdata.params["chaincodeID"] = 
                {
                    path : "github.com/hyperledger/fabric/examples/chaincode/go/chaincode_counter"
                };
            } 
            else 
            {
                JSONdata.params["chaincodeID"] = 
                {
                    name: ccId
                };

            }
            return JSONdata;
        }
        //JSON-RPC 실행
        function executeJsonRpc(url_exec, JSONdata, success, error)
        {
            $.ajax({
                type : 'post',
                url: url_exec,
                data : JSON.stringify(JSONdata),
                contentType: 'application/JSON;',
                dataType: 'JSON',
                scriptCharset: 'utf-8',
                success: function(data)
                {
                    success(data);
                },
                error: function(data)
                {
                    error(data);
                }
            });
        }
    </script>
</head>
<body>
    <p>
        사용자 이름:&nbsp;<input type = "text" id= "userName" value = "test_user0">&nbsp; 패스워드:&nbsp;
        <input type = "text" id = "password" value = "MS9qrN8hFjlE">&nbsp; <input type = "button" value = "login" onclick = "login();"/>
    </p>
    <p>
        <input type = "button" value = "deploy" onclick = "deploy();" />
    </p>
    <table id = "list" border = "1">
        <tr>
            <th></th>
            <th>name</th>
            <th>Lock</th>
        </tr>
    </table>
    <br />
    <input type = "button" value = "lock" onclick = "Lock();" />
    <br />
    <input type = "button" value= "refresh" onclick = "refresh();" />
    <br />
    <input type = "button" value= "unlock" onclick = "Unlock();" />
</body>
</html>