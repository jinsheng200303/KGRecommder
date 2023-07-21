package cn.hunnu.recommender.user.controller;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.service.PersonRoleService;
import cn.hunnu.recommender.user.service.PersonService;
import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "绘图模块", tags = "绘图模块")
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRoleService personRoleService;

    @GetMapping("/politics")
    public Result getPoliticsNum() {
        List<Person> list = personService.list();
        int LeagueMembers = 0; // 共青团员
        int cpcMembers = 0; // 中共党员
        int democracyMembers = 0; // 民主党派
        int masses = 0; // 群众
        int other = 0; // 其他
        for (Person person : list) {
            String politics = person.getPolitics();
            switch (politics){
                case "10": LeagueMembers += 1; break;
                case "20": cpcMembers += 1; break;
                case "30": democracyMembers += 1; break;
                case "40": masses += 1; break;
                default: other += 1;break;
            }
        }
        return Result.success(CollUtil.newArrayList(LeagueMembers,cpcMembers,democracyMembers,masses,other));
    }

    @GetMapping("/age")
    public Result getAgeNum() {
        List<Person> list = personService.list();
        int Juvenile = 0; // 0-18岁
        int youth = 0; // 19-22岁
        int adult = 0; // 23-35岁
        int midlife = 0; // 36-60
        int elderly = 0; //61+
        int other = 0; // age < 0
        for (Person person : list) {
            int age = person.getAge();
            if(age >= 0 && age <= 18){
                Juvenile++;
            } else if (age >= 19 && age <= 22){
                youth++;
            } else if (age >= 23 && age <= 35){
                adult++;
            } else if (age >= 36 && age <= 60) {
                midlife++;
            } else if (age >= 61) {
                elderly++;
            }else {
                other++;
            }
        }
        return Result.success(CollUtil.newArrayList(Juvenile,youth,adult,midlife,elderly,other));
    }

    @GetMapping("/role")
    public Result getRoleNum() {
        List<PersonRole> list = personRoleService.list();
        int student = 0; // 学生
        int teacher = 0; // 教师
        int manager = 0; // 管理员
        int other = 0; // 其他
        for (PersonRole personRole : list) {
            Integer roleId = personRole.getRoleId();
            switch (roleId){
                case 1: student += 1; break;
                case 2: teacher += 1; break;
                case 3: manager += 1; break;
                default: other += 1; break;
            }
        }
        return Result.success(CollUtil.newArrayList(student,teacher,manager,other));
    }
}
