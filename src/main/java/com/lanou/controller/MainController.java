package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dllo on 17/8/30.
 */
@Controller
public class MainController {
    @Resource
    private CostService costService;


    //首页
    @RequestMapping(value = "/")
    public String frontPage() {
        return "index";
    }


    //进入资费管理页面和各种事后重定向
    @RequestMapping("/fee")
    public String fee() {
        return "fee/fee_list";
    }

    //   显示所有和分页
    @RequestMapping(value = "/cost", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> fee(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {
        Map<String, Object> mav = new HashMap<String, Object>();
        //进入这个页面需要遍历数据库已有信息
        PageInfo<Cost> pageCost = null;
        try {
            pageCost = costService.allCostView(pageNum, pageSize);
            mav.put("pageCost", pageCost);
            mav.put("msg", "成功!");

        } catch (MyException e) {
            mav.put("msg", e.getMessage());
        }
        return mav;
    }

    //跳转到添加页面
    @RequestMapping("/fee_add")
    public String fee_add() {

        return "fee/fee_add";
    }

    //判断添加的名字数据库中存不存在,存在提示被占用
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> add(@RequestParam("costName") String costName) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        Boolean nameTf = costService.booleanName(costName);
        if (nameTf) {
            map.put("Tf", true);
        } else {
            map.put("Tf", false);
        }
        return map;
    }

    //添加
    @RequestMapping("/addInsert")
    public String addInsert(Cost cost) {
        costService.insert(cost);
        return "fee/fee_list";
    }

    //删除
    @RequestMapping("/deleteFee")
    @ResponseBody
    public Boolean deleteFee(@RequestParam("costId") Integer id) {
        Boolean tf = costService.deleteFee(id);
        return tf;
    }

    //调到"fee/fee_modi"页面 并得到id
    @RequestMapping("/fee_modify")
    public String fee_modify(@RequestParam("costId") Integer id, HttpSession session) {
        session.setAttribute("costId", id);
        return "fee/fee_modi";
    }

    //修改的回显
    @RequestMapping("/viewRoll")
    @ResponseBody
    public Cost viewRoll(HttpSession session) {
        Integer id = (Integer) session.getAttribute("costId");
        System.out.println("viewRoll"+id);
        //        通过id找cost回显
        Cost cost = costService.findById(id);
        return cost;
    }

    //真正修改
    @RequestMapping("/saveModi")
    public String saveModi(Cost cost){
        System.out.println(cost);
        Boolean tf = costService.saveModi(cost);
        return "fee/fee_list";
    }
    //启用
    @RequestMapping("/startFee")
    public String startFee(@RequestParam("id") Integer id){
        costService.startFee(id);
        return "fee/fee_list";
    }

}
