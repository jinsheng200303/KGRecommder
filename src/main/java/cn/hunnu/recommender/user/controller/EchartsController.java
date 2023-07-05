package cn.hunnu.recommender.user.controller;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.service.PersonRoleService;
import cn.hunnu.recommender.user.service.PersonService;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/gender")
    public Result getGenderNum() {
        List<Person> list = personService.list();
        int male = 0; // 男
        int female = 0; // 女
        int other = 0; // 其他
        for (Person person : list) {
            String gender = person.getGender();
            switch (gender){
                case "M": male += 1; break;
                case "F": female += 1; break;
                default: other += 1;break;
            }
        }
        return Result.success(CollUtil.newArrayList(male,female,other));
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
