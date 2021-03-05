package com.jincong.springboot.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;
import com.jincong.springboot.result.BaseResult;
import com.jincong.springboot.service.IGoodsService;
import com.jincong.springboot.service.IOrderService;
import com.jincong.springboot.vo.GoodsCategoryVO;
import com.jincong.springboot.vo.QueryUserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 接口Controller
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/07/28
 */

// RestController 注解相当于@Controller和@ResponseBody两个注解
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {


    @Autowired
    IOrderService orderService;


    @Autowired
    IGoodsService goodsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    /**
     * 导出百万数据性能测试
     * @return
     */
    @GetMapping("/export")
    public BaseResult export(@RequestParam(name = "startTime")  DateTime startTime,
                             @RequestParam(name = "endTime")  DateTime endTime,  HttpServletResponse response) throws Exception{


        long start = System.currentTimeMillis();
        String fileName = URLEncoder.encode(String.format("%s-(%s).xlsx", "订单支付数据", UUID.randomUUID().toString()),
                StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        ExcelWriter writer = new ExcelWriterBuilder()
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLS)
                .file(response.getOutputStream())
                .build();


        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("target");

        long lastBatchMaxId = 0L;
        int limit = 500;

        for(;;) {
            List<OrderDTO> orderList = orderService.listOrdersByScrollingPagination(lastBatchMaxId, limit, startTime, endTime);
            if (CollectionUtil.isEmpty(orderList)) {
                break;
            } else {
                lastBatchMaxId = orderList.stream().map(OrderDTO::getId).max(Long::compareTo).orElse(Long.MAX_VALUE);
                writer.write(orderList, writeSheet);
            }

        }
        log.info("导出数据耗时:{} ms,start:{},end:{}", System.currentTimeMillis() - start, startTime, endTime);


        return new BaseResult(true);
    }


    @PostMapping("/addOrder")
    public boolean addOrder(@RequestBody QueryUserVO userVO) {

        long start = System.currentTimeMillis();
        int count = 5000000;

        for (int i = 0; i < count; i++) {
            TOrder order = new TOrder();
            order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
            order.setAmount(new BigDecimal(new Random().nextInt(1000)));
            order.setPaymentTime(new Date());
            order.setOrderStatus((byte) ((byte) i % 3));
            orderService.insertOrder(order);
        }

        log.info("插入耗时:{} ms,start:{},end:{}", System.currentTimeMillis() - start, start);


        return true;
    }

    @ApiOperation(value = "获取商品分类树")
    @RequestMapping(value = "/getGoodsCategoryTree", method = RequestMethod.GET)
    public BaseResult findAllUser() {

        List<GoodsCategoryVO> goodsCategories = goodsService.listTree();

        return new BaseResult<>(goodsCategories, "获取商品分类树");
    }


    @ApiOperation(value = "获取商品分类树")
    @GetMapping(value = "/getStockByDB")
    public BaseResult getStockByDB() {
        int id = 1;
        int reamingStock = orderService.getStockFromDB(id);
        log.info("商品Id: [{}] 剩余库存为: [{}]", id, reamingStock);

        return new BaseResult<>(reamingStock, "获取商品分类树");
    }

    @ApiOperation(value = "获取商品分类树")
    @GetMapping(value = "/getStockByRedis")
    public BaseResult getStockByRedis() {
        int id = 1;
        int reamingStock = orderService.getStockFromRedis(id);

        return new BaseResult<>(reamingStock, "获取商品分类树");
    }
}
