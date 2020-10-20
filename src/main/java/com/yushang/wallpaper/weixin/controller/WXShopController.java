package com.yushang.wallpaper.weixin.controller;

import com.yushang.wallpaper.common.config.aop.WxAop;
import com.yushang.wallpaper.layer.service.user.UserService;
import com.yushang.wallpaper.weixin.service.WXShopService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商城接口
 */
@RestController
@RequestMapping(value = "weixin/shop")
@Api(value = "商城api",tags = "shop",description = "商城api接口")
public class WXShopController {

    @Autowired
    private WXShopService wxShopService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获得商品列表",notes = "获得咖啡列表,默认返回10条")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @RequestMapping(value = "shopList",method = RequestMethod.GET,produces = "application/json")
    @WxAop
    public Map<String,Object> shopList(
           @ApiParam(name = "type",value = "类型  1 咖啡  2 自行车商城  3 周边",required = true) @RequestParam(defaultValue = "1") Integer type,
           @ApiParam(name = "page",value = "页码",required = true) @RequestParam(defaultValue = "1") Integer page,
           @ApiParam(name = "size",value = "条数",required = true)  @RequestParam(defaultValue = "10") Integer size){
        return wxShopService.shopList(type,page,size);
    }


    @ApiOperation(value = "获得商品分类",notes = "获得商品分类,返回全部分类信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常",responseContainer = "你好"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @RequestMapping(value = "shopLabel",method = RequestMethod.GET,produces = "application/json")
    @WxAop
    public Map<String,Object> shopLabel(
            @ApiParam(name = "typeId",required = true,value = "分类类型 1 咖啡  2 商城  3 周边",defaultValue = "1") @RequestParam Byte typeId){
        return wxShopService.shopLabel(typeId);
    }


    @ApiOperation(value = "加入购物车",notes = "商品加入购物车")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",value = "商品id",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "productSkuId",value = "商品skuId",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "number",value = "购买数量",required = false,paramType = "query",dataType = "Short",defaultValue = "1"),
            @ApiImplicitParam(name = "sendType",value = "配送方式",required = false,paramType = "query",dataType = "Byte",defaultValue = "2"),
    })
    @RequestMapping(value = "addShoppingCart")
    @WxAop
    public  Map<String,Object> addShoppingCart(
            @RequestParam Integer productId,@RequestParam Integer userId,@RequestParam Integer productSkuId,
            Short number,Byte sendType ){
        return wxShopService.addShoppingCart(productId,userId,productSkuId,number,sendType);
    }


    /**
     * 商城   维修保养
     */
    @ApiOperation(value = "维修保养",notes = "维修保养列表")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude",value = "经度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "latitude",value = "纬度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "query",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "size",value = "条数",required = true,paramType = "query",dataType = "Integer",defaultValue = "10")
    })
    @RequestMapping(value = "getWXBaoYang")
    @WxAop
    public Map<String,Object> getWXBaoYang(
            @RequestParam(value = "longitude") String longitude,
            @RequestParam(value = "latitude") String latitude,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size)  {
        return wxShopService.getWXBaoYang(page, size, longitude, latitude);
    }

    /**
     * 通过用户id以及商户id预约保养
     * 发送短信通知预约人
     */
    @ApiOperation(value = "预约保养",notes = "通过用户id以及商户id预约保养,并发送短信通知预约人")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subscribeDay",value = "天数",required = true,paramType = "query"),
            @ApiImplicitParam(name = "subscribeTime",value = "时间",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "商户id",required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "addSubsribe")
    @WxAop
    public Map<String,Object> addSubsribe(
            @RequestParam(value = "shopId") Integer shopId,
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "subscribeDay") String subscribeDay,
            @RequestParam(value = "subscribeTime") String subscribeTime)  {
        return wxShopService.addSubsribe(shopId, userId, subscribeDay, subscribeTime);
    }


    /**
     * 创建订单
     */
    /*@ApiOperation(value = "创建订单",notes = "创建订单")
    @ApiResponses({
            @ApiResponse(code = 200,message = "响应正常"),
            @ApiResponse(code = 400,message = "响应失败")
    })
    @WxAop
    @RequestMapping(value="saveOrderInfo")
    public  Map<String,Object> saveOrderInfo(TbOrder order)throws Exception{
            //总价格
            BigDecimal allMoney = new BigDecimal(0);
            //得到商品信息值  jsonArray数组
            JSONArray json = new JSONArray(order.getJsonArray());
            for(int i=0;i<json.length();i++){
                JSONObject jsonObject = json.getJSONObject(i);
                Short number = Short.valueOf(jsonObject.get("number").toString());
                BigDecimal productPrice = new BigDecimal(jsonObject.get("productPrice").toString());
                BigDecimal bigDecimal = new BigDecimal(number);
                BigDecimal multiply = productPrice.multiply(bigDecimal);
                allMoney.add(multiply);
            }
            DecimalFormat df = new DecimalFormat("0.00");
            //得到积分
            Integer userScore = userService.selectUserScoreById(order.getUserId());
            BigDecimal bigDecimal = new BigDecimal(userScore);
            BigDecimal bigDecimal1 = new BigDecimal(100);
            BigDecimal divide = bigDecimal.divide(bigDecimal1);
            BigDecimal subtract = allMoney.subtract(divide);
            BigDecimal subtract2 = new BigDecimal(0);
            if(subtract.compareTo(subtract2) <= 0){
                subtract = new BigDecimal(0);
            }
            //支付价格
            BigDecimal payMoney = order.getPayMoney();
            if(payMoney.compareTo(subtract) == 0){
                String orderNo = weiXinPayService.setOrderInfo(order);
                return ResultFul.getRowsMap(orderNo);
            }else{
                return ResultFul.getMessageMap("商品实际支付价格不正确");
            }

    }
*/

}
