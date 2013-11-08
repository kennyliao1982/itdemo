<#-- 行銷點數付款完成 email -->
ihergo愛合購 行銷點數付款完成

Hi ${name},<br/><br/>
ihergo已經收到您的付款並已將行銷點數儲值至您的商店中，感謝您訂購ihergo行銷點數。<br/><br/>
訂購時間：${orderDate?string("yyyy-MM-dd HH:mm:ss")}<br/>
行銷點數：${point} 點<br/>
付款金額：${amount} 元<br/>
付款時間：${payDate?string("yyyy-MM-dd HH:mm:ss")}<br/>
付款帳號：${payAccount}<br/><br/>
若您有任何問題，請來信service@ihergo.com，我們會盡快為您解決！<br/><br/>
ihergo愛合購 - 團購最便宜 <a href="http://www.ihergo.com">www.ihergo.com</a>
${dataList?size}

