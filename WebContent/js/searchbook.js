    var xmlHttp=false;
    function createXMLHttpRequest()
    {
        if (window.ActiveXObject)  //在IE浏览器中创建XMLHttpRequest对象
        {
            try{
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch(e){
                try{
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch(ee){
                    xmlHttp=false;
                }
            }
        }
        else if (window.XMLHttpRequest) //在非IE浏览器中创建XMLHttpRequest对象
        {
            try{
                xmlHttp = new XMLHttpRequest();
            }
            catch(e){
                xmlHttp=false;
            }
        }
    }
    function searchcheck(){
        var searchinput = document.getElementById("searchinput").value;
        createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
        xmlHttp.onreadystatechange=readercheckResult;   //设置回调函数
        var url="BookAction?action=Searchbook &searchinput=" + searchinput;
        xmlHttp.open("POST",url,true);      //向服务器端发送请求
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
        xmlHttp.send(null);
    }

	
	function errorsubmit(){
		var readerid = document.getElementById("searchinput").value;
		var readeridcheck =  document.getElementById("searchcheck").innerHTML;
		if(readerid == ""){
			document.getElementById("searchcheck").innerHTML = "*搜索内容为空";
			return false;
		}
	}