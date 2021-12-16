<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="../css/shopCart.css" rel="stylesheet" type="text/css">
    <script src="../js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/cycle.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/json2.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/Tools.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/shopCart.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/index.js" type="text/javascript" charset="utf-8"></script>
    <title></title>
</head>
<body>

<div id="head">
    <div id="Super_link">

        <div id="head_Super_link">
            <div class="img"><img src="../img/Shopping_cart.png"></div>
            <div id= "goToShopCart" class="a"><a href="#">购物车</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="#">我的订单</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="#">个人中心</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="#">商品分类</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="#">收藏夹</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="#">联系客服</a></div>
            <div class="img"><img src="../img/line.png"></div>
            <div class="a"><a href="../html/index.html">网站首页</a></div>
        </div>
    </div>
</div>

<div class="car">

    <div class="good">
        <table><tr><td width=30%>商品名称</td><td width=20%>单价</td><td width=20%>数量</td><td width=30%>操作</td></tr></table>
    </div>

    <div class="production">

        <div class="goods">
            <table>
                <tr>
                    <td width=30%>${p_name}</td>
                    <td width=20%><a>${p_price}</a></td>
                    <td width=20%><button class="sub">-</button><a> ${p_nums} </a><button class="add">+</button> </td>
                    <td width=30%><a><button class="btn1">删除</button></a></td></tr>
            </table>
        </div>
    </div>
    <div class="goods2">
        <table><tr><td width=560></td><td width=20%>小计：</td><td>总数：</td></tr></table>
    </div>
    <div class="goods1">
        <table><tr><td width=50%><button class="btn2">确认购买</button></td><td><button class="btn3">全部清空</button></td></tr></table>
    </div>

</div>
</body>
</html>

