package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.entity.QuestionKnowledge;
import cn.hunnu.recommender.examination.entity.Resources;
import cn.hunnu.recommender.examination.entity.StudentPaper;
import cn.hunnu.recommender.examination.mapper.*;
import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.dto.PersonStylePaperQuery;
import cn.hunnu.recommender.user.entity.PersonStylePaper;
import cn.hunnu.recommender.user.mapper.BrowseRecordsMapper;
import cn.hunnu.recommender.user.mapper.PersonKnowledgeMapper;
import cn.hunnu.recommender.user.mapper.PersonStylePaperMapper;
import cn.hunnu.recommender.user.serviceImpl.BrowseRecordsServiceImpl;
import cn.hunnu.recommender.user.serviceImpl.PersonKnowledgeServiceImpl;
import cn.hunnu.recommender.user.vo.PersonStylePaperVO;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户风格测试数据存储 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/person-style-paper")
@Api(value = "用户风格测试数据存储模块",tags = "用户风格测试数据存储模块")
public class PersonStylePaperController extends userBaseController {

    @Autowired
    PersonStylePaperMapper personStylePaperMapper;

    @Autowired
    BrowseRecordsMapper browseRecordsMapper;

    @Autowired
    RecordsMapper recordsMapper;

    @Autowired
    ResourcesMapper resourcesMapper;

    @Autowired
    QuestionsMapper questionsMapper;

    @Autowired
    QuestionKnowledgeMapper questionKnowledgeMapper;

    @Autowired
    PersonKnowledgeServiceImpl personKnowledgeService;

    @ApiOperation(value = "用户风格测试数据存储列表",notes = "用户风格测试数据存储列表")
    @GetMapping("/list")
    public List<PersonStylePaper> list() {
        return personStylePaperService.list();
    }

    //根据ID删除用户风格测试数据存储
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户风格测试数据存储",notes = "删除用户风格测试数据存储")
    public Result delete(@RequestBody List<Integer> IDS){
        personStylePaperService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "用户风格测试数据存储信息查询",notes = "用户风格测试数据存储信息查询")
    public Result<Page<PersonStylePaper>> queryPersonStylePaperInfo(@RequestBody PersonStylePaperQuery personStylePaperQuery){


        LambdaQueryWrapper<PersonStylePaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PersonStylePaper::getId);

        if(!"".equals(personStylePaperQuery.getUserId())&& personStylePaperQuery.getUserId()!=null){
            wrapper.eq(PersonStylePaper::getUserId, personStylePaperQuery.getUserId());
        }

        Page<PersonStylePaper> page = personStylePaperService.page(
                new Page<>(
                        personStylePaperQuery.getPageNum(),
                        personStylePaperQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "用户提交测试卷保存数据",notes = "用户提交测试卷保存数据")
    @PostMapping("/save")
    public Result save(@RequestBody PersonStylePaperVO personStylePaperVO){
        PersonStylePaper personStylePaper = new PersonStylePaper();
        personStylePaper.setUserId(personStylePaperVO.getUserId());
        personStylePaper.setAnswers(personStylePaperVO.getAnswers());
        //设置不可重复提交
        if (personStylePaperVO.getUserId() == null){
            List<PersonStylePaper> list = personStylePaperService.list(new QueryWrapper<PersonStylePaper>()
                    .eq("user_id", personStylePaperVO.getUserId()));
            if (CollUtil.isNotEmpty(list)){
                throw new CustomException(-3, "您已经提交过测试卷！");
            }
        }
        JSONArray json = JSON.parseArray(personStylePaperVO.getAnswers()); // 首先把字符串转成 JSONArray  对象
        //填充试题知识点矩阵和学生得分矩阵
        if(json.size()>0){
            int j = 0;
            int[] answerANum = new int[5];
            int[] answerBNum = new int[5];
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonArray 数组，把每一个对象转成 json 对象
                if(job.get("studentAnswer").equals("A")){
                    answerANum[Integer.parseInt(job.get("questionType").toString())]++;
                }else {
                    answerBNum[Integer.parseInt(job.get("questionType").toString())]++;
                }
            }
            if(answerANum[1] - answerBNum[1] >= 5){
                personStylePaper.setStudyStyleId1(1);
            } else if (answerBNum[1] - answerANum[1] >= 5) {
                personStylePaper.setStudyStyleId1(2);
            }else {
                personStylePaper.setStudyStyleId1(9);
            }
            if(answerANum[2] - answerBNum[2] >= 5){
                personStylePaper.setStudyStyleId2(3);
            } else if (answerBNum[2] - answerANum[2] >= 5) {
                personStylePaper.setStudyStyleId2(4);
            }else {
                personStylePaper.setStudyStyleId2(9);
            }
            if(answerANum[3] - answerBNum[3] >= 5){
                personStylePaper.setStudyStyleId3(5);
            } else if (answerBNum[3] - answerANum[3] >= 5) {
                personStylePaper.setStudyStyleId3(6);
            }else {
                personStylePaper.setStudyStyleId3(9);
            }
            if(answerANum[4] - answerBNum[4] >= 5){
                personStylePaper.setStudyStyleId4(5);
            } else if (answerBNum[4] - answerANum[4] >= 5) {
                personStylePaper.setStudyStyleId4(6);
            }else {
                personStylePaper.setStudyStyleId4(9);
            }
            System.out.println(personStylePaper.getId());
            personStylePaperService.saveOrUpdate(personStylePaper);
        }
        return Result.success();
    }

    @ApiOperation(value = "获取学习资源推荐",notes = "获取学习资源推荐")
    @PostMapping("/resource-recommend")
    public Result RecommendResourceTopic(@RequestParam Integer userId){
        PersonStylePaper personStylePaper = new PersonStylePaper();
        personStylePaper = personStylePaperMapper.findUserInfo(userId);
        List<Integer> studyStyle1 = new ArrayList<Integer>();
        int videoPreference = 0;
        int documentationPreference = 0;
        if(personStylePaper.getStudyStyleId1()==9){
            studyStyle1.add(1);
            studyStyle1.add(2);
            studyStyle1.add(9);
        }else {
            studyStyle1.add(personStylePaper.getStudyStyleId1());
            if(personStylePaper.getStudyStyleId1()==1){
                videoPreference = 1;
            }else {
                documentationPreference = 1;
            }
        }
        List<Integer> studyStyle2 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId2()==9){
            studyStyle2.add(3);
            studyStyle2.add(4);
            studyStyle2.add(9);
        }else {
            studyStyle2.add(personStylePaper.getStudyStyleId2());
            if(personStylePaper.getStudyStyleId2()==4){
                videoPreference = 1;
            }else {
                documentationPreference = 1;
            }
        }
        List<Integer> studyStyle3 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId3()==9){
            studyStyle3.add(5);
            studyStyle3.add(6);
            studyStyle3.add(9);
        }else {
            studyStyle3.add(personStylePaper.getStudyStyleId3());
            if(personStylePaper.getStudyStyleId3()==5){
                videoPreference = 1;
            }else {
                documentationPreference = 1;
            }
        }
        List<Integer> studyStyle4 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId4()==9){
            studyStyle4.add(7);
            studyStyle4.add(8);
            studyStyle4.add(9);
        }else {
            studyStyle4.add(personStylePaper.getStudyStyleId4());
            if(personStylePaper.getStudyStyleId4()==8){
                videoPreference = 1;
            }else {
                documentationPreference = 1;
            }
        }
        List<Integer> userList = personStylePaperMapper.findUsersId(studyStyle1,studyStyle2,studyStyle3,studyStyle4);
        Integer N = userList.size();
        int[][] sparseMatrix=new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                sparseMatrix[i][j] = 0;
        }
        Map<Integer, Integer> userResourceLength = new HashMap<>();//存储每一个用户对应的浏览记录总数  eg: A 3
        Map<Integer, Set<Integer>> userResourceCollection = new HashMap<>();//建立浏览记录到用户的倒排表 eg: a A B
        Set<Integer> resources = new HashSet<>();//辅助存储浏览记录集合
        Map<Integer, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射:user->id
        Map<Integer, Integer> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射:id->user
        for(int i=0;i<N;i++){//依次处理N个用户
            Integer user=userList.get((int)i);
            List<Integer> resourceList = browseRecordsMapper.getResourceListByUserID(user);
            userResourceLength.put(user,resourceList.size());
            //用户ID与稀疏矩阵建立对应关系
            userID.put(user,i);
            idUser.put(i,user);
            //建立资源--用户倒排表
            for(int j=0;j<resourceList.size();j++){
                Integer topic=resourceList.get(j);
                if(resources.contains(topic)){//如果已经包含对应的资源--用户映射，直接添加对应的用户
                    userResourceCollection.get(topic).add(user);
                }else{//否则创建对应资源--用户集合映射
                    resources.add(topic);
                    //创建资源--用户倒排关系
                    userResourceCollection.put(topic,new HashSet<Integer>());
                    userResourceCollection.get(topic).add(user);
                }
            }
        }
        //输出倒排关系表
        System.out.println("输出倒排关系表：\n"+userResourceCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Map.Entry<Integer, Set<Integer>>> entrySet = userResourceCollection.entrySet();
        Iterator<Map.Entry<Integer, Set<Integer>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Set<Integer> commonUsers=iterator.next().getValue();
            for(Integer user_u:commonUsers){
                for(Integer user_v:commonUsers){
                    if(user_u==user_v){
                        continue;
                    }
                    //计算用户u与用户v都有正反馈的资源总数
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)]+=1;
                }
            }
        }
        //计算用户之间的相似度【余弦相似性】
        Integer recommendUserId = userID.get(userId);
        for(int j=0;j<sparseMatrix.length;j++){
            if(j!=recommendUserId){
                System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"
                        +sparseMatrix[recommendUserId][j]/Math.sqrt(userResourceLength.get(idUser.get(recommendUserId))*userResourceLength.get(idUser.get(j))));
            }
        }
        //计算指定用户recommendUser的资源推荐度
        Map<Integer,Double> resourceRecommendDegree=new HashMap<>();//topic->推荐度
        for(Integer resource:resources){//遍历每一件资源
            //得到购买当前资源的所有用户集合
            Set<Integer> users=userResourceCollection.get(resource);
            //如果被推荐用户没有购买当前资源，则进行推荐度计算
            if(!users.contains(userId)){
                double RecommendDegree = 0.0;
                for(Integer user:users){
                    //推荐度计算
                    RecommendDegree+=sparseMatrix[userID.get(userId)][userID.get(user)]/Math.sqrt(userResourceLength.get(userId)*userResourceLength.get(user));
                    double comprehension = personKnowledgeService.findComprehension(userId,resource);
                    RecommendDegree = RecommendDegree * 0.3 - comprehension * 0.7;
                }
                if(videoPreference==1 && documentationPreference==0){
                    if(resourcesMapper.findResourceType(resource).equals("视频")){
                        RecommendDegree *= 2;
                    }
                }
                if(videoPreference==0 && documentationPreference==1){
                    if(resourcesMapper.findResourceType(resource).equals("文档")){
                        RecommendDegree *= 2;
                    }
                }
                resourceRecommendDegree.put(resource,RecommendDegree);
            }
        }
        System.out.println("TopicID,推荐度\n"+resourceRecommendDegree);
        //取最大的5个
        if(resourceRecommendDegree.size()<5){
            List<Integer> list = resourceRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, resourceRecommendDegree.size());
            List<Integer> list1 = new ArrayList<Integer>();
            if(videoPreference==1 && documentationPreference==0){
                list1 = resourcesMapper.findIDVideo();
            }
            else if(videoPreference==0 && documentationPreference==1){
                list1 = resourcesMapper.findIDDocumentation();
            }else {
                list1 = resourcesMapper.findID();
            }
            for(int i=0;i<list1.size();i++){
                if(list.size()==5)
                    break;
                if(list.contains(list1.get(i)))
                    continue;
                list.add(list1.get(i));
            }
            System.out.println(list);
            return Result.success(resourcesMapper.findByTopicIdIn(list));
        }else {
            List<Integer> list = resourceRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, 5);
            System.out.println(list);
            return Result.success(resourcesMapper.findByTopicIdIn(list));
        }
    }


    @ApiOperation(value = "获取试题资源推荐",notes = "获取试题资源推荐")
    @PostMapping("/question-recommend")
    public Result RecommendQuestionTopic(@RequestParam Integer userId){
        PersonStylePaper personStylePaper = new PersonStylePaper();
        personStylePaper = personStylePaperMapper.findUserInfo(userId);
        List<Integer> studyStyle1 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId1()==9){
            studyStyle1.add(1);
            studyStyle1.add(2);
            studyStyle1.add(9);
        }else {
            studyStyle1.add(personStylePaper.getStudyStyleId1());
        }
        List<Integer> studyStyle2 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId2()==9){
            studyStyle2.add(3);
            studyStyle2.add(4);
            studyStyle2.add(9);
        }else {
            studyStyle2.add(personStylePaper.getStudyStyleId2());
        }
        List<Integer> studyStyle3 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId3()==9){
            studyStyle3.add(5);
            studyStyle3.add(6);
            studyStyle3.add(9);
        }else {
            studyStyle3.add(personStylePaper.getStudyStyleId3());
        }
        List<Integer> studyStyle4 = new ArrayList<Integer>();
        if(personStylePaper.getStudyStyleId4()==9){
            studyStyle4.add(7);
            studyStyle4.add(8);
            studyStyle4.add(9);
        }else {
            studyStyle4.add(personStylePaper.getStudyStyleId4());
        }
        List<Integer> userList = personStylePaperMapper.findUsersId(studyStyle1,studyStyle2,studyStyle3,studyStyle4);
        Integer N = userList.size();
        int[][] sparseMatrix=new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                sparseMatrix[i][j] = 0;
        }
        Map<Integer, Integer> userQuestionLength = new HashMap<>();//存储每一个用户对应的浏览记录总数  eg: A 3
        Map<Integer, Set<Integer>> userQuestionCollection = new HashMap<>();//建立浏览记录到用户的倒排表 eg: a A B
        Set<Integer> questions = new HashSet<>();//辅助存储浏览记录集合
        Map<Integer, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射:user->id
        Map<Integer, Integer> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射:id->user
        for(int i=0;i<N;i++){//依次处理N个用户
            Integer user=userList.get((int)i);
            List<Integer> questionList = recordsMapper.getQuestionListByUserID(user);
            userQuestionLength.put(user,questionList.size());
            //用户ID与稀疏矩阵建立对应关系
            userID.put(user,i);
            idUser.put(i,user);
            //建立资源--用户倒排表
            for(int j=0;j<questionList.size();j++){
                Integer topic=questionList.get(j);
                if(questions.contains(topic)){//如果已经包含对应的资源--用户映射，直接添加对应的用户
                    userQuestionCollection.get(topic).add(user);
                }else{//否则创建对应资源--用户集合映射
                    questions.add(topic);
                    //创建资源--用户倒排关系
                    userQuestionCollection.put(topic,new HashSet<Integer>());
                    userQuestionCollection.get(topic).add(user);
                }
            }
        }
        //输出倒排关系表
        System.out.println("输出倒排关系表：\n"+userQuestionCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Map.Entry<Integer, Set<Integer>>> entrySet = userQuestionCollection.entrySet();
        Iterator<Map.Entry<Integer, Set<Integer>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Set<Integer> commonUsers=iterator.next().getValue();
            for(Integer user_u:commonUsers){
                for(Integer user_v:commonUsers){
                    if(user_u==user_v){
                        continue;
                    }
                    //计算用户u与用户v都有正反馈的资源总数
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)]+=1;
                }
            }
        }
        //计算用户之间的相似度【余弦相似性】
        Integer recommendUserId = userID.get(userId);
        for(int j=0;j<sparseMatrix.length;j++){
            if(j!=recommendUserId){
                System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"
                        +sparseMatrix[recommendUserId][j]/Math.sqrt(userQuestionLength.get(idUser.get(recommendUserId))*userQuestionLength.get(idUser.get(j))));
            }
        }
        //计算指定用户recommendUser的资源推荐度
        Map<Integer,Double> questionRecommendDegree=new HashMap<>();//topic->推荐度
        Integer knowledgeId = personKnowledgeService.findLeastKnowledgeId(userId);
        if(knowledgeId!=0){
            List<Integer> questionId = questionKnowledgeMapper.findQuestionsId(knowledgeId);
            for (int i = 0; i < questionId.size(); i++){
                double RecommendDegree = 0.5-personKnowledgeService.findQuestionComprehension(userId,questionId.get(i))*0.7;;
                questionRecommendDegree.put(questionId.get(i),RecommendDegree);
            }
        }
        for(Integer question:questions){//遍历每一件资源
            //得到购买当前资源的所有用户集合
            Set<Integer> users=userQuestionCollection.get(question);
            //如果被推荐用户没有购买当前资源，则进行推荐度计算
            if(!users.contains(userId)){
                double RecommendDegree = 0.0;
                for(Integer user:users){
                    //推荐度计算
                    RecommendDegree+=sparseMatrix[userID.get(userId)][userID.get(user)]/Math.sqrt(userQuestionLength.get(userId)*userQuestionLength.get(user));
                    double comprehension = personKnowledgeService.findQuestionComprehension(userId,question);
                    RecommendDegree = RecommendDegree * 0.3 - comprehension * 0.7;
                    if (comprehension>0){
                        RecommendDegree+=0.5;
                    }
                }
                questionRecommendDegree.put(question,RecommendDegree);
            }
        }
        System.out.println("TopicID,推荐度\n"+questionRecommendDegree);
        //取最大的5个
        if(questionRecommendDegree.size()<5){
            List<Integer> list = questionRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, questionRecommendDegree.size());
            List<Integer> list1=questionsMapper.findID();
            for(int i=0;i<list1.size();i++){
                if(list.size()==5)
                    break;
                if(list.contains(list1.get(i)))
                    continue;
                list.add(list1.get(i));
            }
            System.out.println(list);
            return Result.success(questionsMapper.findByTopicIdIn(list));
        }else {
            List<Integer> list = questionRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, 5);
            System.out.println(list);
            return Result.success(questionsMapper.findByTopicIdIn(list));
        }
    }
}
