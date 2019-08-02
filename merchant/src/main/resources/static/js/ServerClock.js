/*
名称：服务器时钟（一次读取，实时显示）
功能：在客户端浏览器上显示服务器端的时间。
原理：	
	算法步骤：
	1. 获取服务端的日期时间。
	2. 根据客户端浏览器的时间可以得到服务器和客户端的时间差。
	3. 服务器的时钟 = 客户端的时钟（变化值）+ 时间差（固定值）
	
	这样客户端就没有必要实时的到服务器端去取时间。

说明：
	1. 多浏览器支持
	2. 由于网络延时无法估计的原因，会有一定的误差。
	    用户可以通过 set_delay() 方法来减少误差。
参数：
	s_year, s_month, s_day, s_hour, s_min, s_sec  
	分别为服务器端的 年 月 日 时 分 秒，

	例如：2008,9,19,0,9,0 表示 2008年9月19日 0点9分0秒
*/
var ServerClock = function(s_year,s_month,s_day,s_hour,s_min,s_sec)
{
	//估计从服务器下载网页到达客户端的延时时间，默认为1秒。
	var _delay = 1000;
	
	//服务器端的时间
	var serverTime = null;
	if(arguments.length == 0)
	{
		//没有设置服务器端的时间，按当前时间处理
		serverTime = new Date(); 
		_delay = 0;
	}
	else
		serverTime = 
			new Date(s_year,s_month-1,s_day,s_hour,s_min,s_sec);

	//客户端浏览器的时间
	var clientTime = new Date();
	//获取时间差
	var _diff = serverTime.getTime() - clientTime.getTime(); 

	//设置从服务器下载网页到达客户端的延时时间，默认为1秒。
	this.set_delay = function(value){_delay=value;};

	//获取服务的日期时间
	this.get_ServerTime = function(formatstring)
	{
		clientTime = new Date();
		serverTime.setTime(clientTime.getTime()+_diff+_delay);
		if(formatstring == null)
			return serverTime;
		else
			return serverTime.format(formatstring);
	};	
}

/*
Date 扩展成员
*/
Date.MonthNames = new Array(
	'January',
	'February',
	'March',
	'April',
	'May',
	'June',
	'July',
	'August',
	'September',
	'October',
	'November',
	'December'
);

Date.DayNames = new Array(
	'Sunday',
	'Monday',
	'Tuesday',
	'Wednesday',
	'Thursday',
	'Friday',
	'Saturday'
);

Date.MonthNames = new Array(
	'一月',
	'二月',
	'三月',
	'四月',
	'五月',
	'六月',
	'七月',
	'八月',
	'九月',
	'十月',
	'十一月',
	'十二月'
);

Date.DayNames = new Array(
	'星期天',
	'星期一',
	'星期二',
	'星期三',
	'星期四',
	'星期五',
	'星期六'
);

/*
Date format 功能扩展函数
*/
Date.prototype.format=function (format) {
	if(format==null)format="yyyy/MM/dd HH:mm:ss.SSS";
	var year=this.getFullYear();
	var month=this.getMonth();
	var sMonth=Date.MonthNames[month];
	var date=this.getDate();
	var day=this.getDay();
	var hr=this.getHours();
	var min=this.getMinutes();
	var sec=this.getSeconds();
	var daysInYear=Math.ceil((this-new Date(year,0,0))/86400000);
	var weekInYear=Math.ceil((daysInYear+new Date(year,0,1).getDay())/7);
	var weekInMonth=Math.ceil((date+new Date(year,month,1).getDay())/7);
	return format.replace("yyyy",year).replace("yy",year.toString ().substr(2)).
		replace("dd",(date<10?"0":"")+date).replace("HH",(hr<10?"0":"")+hr).
		replace("KK",(hr%12<10?"0":"")+hr%12).replace("kk",(hr>0&&hr<10?"0":"")+(((hr+23)%24)+1)).
		replace("hh",(hr>0&&hr<10||hr>12&&hr<22?"0":"")+(((hr+11)%12)+1)).replace("mm",(min<10?"0":"")+min).
		replace("ss",(sec<10?"0":"")+sec).replace("SSS",this%1000).replace("a",(hr<12?"AM":"PM")).
		replace("W",weekInMonth).replace("F",Math.ceil(date/7)).replace(/E/g,Date.DayNames[day]).
		replace("D",daysInYear).replace("w",weekInYear).replace(/MMMM+/,sMonth).
		replace("MMM",sMonth.substring(0,3)).replace("MM",(month<9?"0":"")+(month+1));
}
