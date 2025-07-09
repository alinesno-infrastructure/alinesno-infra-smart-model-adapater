package com.agentflex.video.wan.test;

import com.agentflex.video.wan.WanVision;
import com.agentflex.video.wan.WanVisionConfig;
import com.agentflex.video.wan.WanVisionModelEnums;
import com.agentsflex.core.vision.BaseVision;
import com.agentsflex.core.vision.GenVideoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StarWarShots {
    public static void main(String[] args) throws IOException {
//        List<String> shotsList = new ArrayList<>();
//
//        // 镜头一画面
//        String shot1 = "浩瀚宇宙中，巨大无比的鸾鸟航天母舰缓缓启动，它那700米的巨大翼展犹如遮天蔽日的钢铁之翼，舰身上的灯光依次亮起，在黑暗宇宙中闪烁，引擎处喷射出蓝色等离子体光芒，照亮周边一片空间。";
//        shotsList.add(shot1);
//
//        // 镜头二画面
//        String shot2 = "从鸾鸟航天母舰的巨大舱门中，多架白帝空天战机如银色闪电般鱼贯而出。战机呈拍扁版苏 - 27的升力体布局，线条流畅。镜头拉近到其中一架，展示其酷炫的机身细节，内置弹舱微微开启，露出先进武器装备。";
//        shotsList.add(shot2);
//
//        // 镜头三画面
//        String shot3 = "在某一星球表面战场上，密密麻麻的蜂群 - 3C无人机从地面发射装置腾空而起，迅速组成各种战斗编队，如蝗虫般向敌方目标扑去。它们利用AI自主编队、智能避障，在天空中灵活穿梭，场面壮观。";
//        shotsList.add(shot3);
//
//        // 镜头四画面
//        String shot4 = "在寒冷的外星冰原上，几只中国“锐爪”机器狼呈战斗队形巡逻前进。它们身上的金属在冰原反射光下闪烁，背上的机枪随着它们的移动灵活转动，扫描周围环境，遇到可疑目标时，眼睛部位红光闪烁锁定目标。";
//        shotsList.add(shot4);
//
//        // 镜头五画面
//        String shot5 = "地球上的一处隐蔽发射基地，“快舟 - 1A”火箭矗立在发射架上，周围工作人员忙碌进行最后的发射准备。随着倒计时结束，火箭点火，尾部喷出强大火焰，快速升空，逐渐消失在云层中，画面拉远展示火箭冲破大气层，前往太空。";
//        shotsList.add(shot5);

        List<String> shots = new ArrayList<>();

        // 镜头一：废土石猴降世
        shots.add("在一片荒芜的废土上，龟裂的大地冒着热气，四周是倒塌的建筑残骸和扭曲的金属。一座巨大的、被岁月侵蚀的石像矗立在焦土中央，突然，石像迸发出刺眼的光芒，石块纷纷剥落，一只浑身散发着奇异能量的石猴从中跃出，它的毛发凌乱而粗糙，眼神中透露出不羁与野性，对着灰暗的天空发出一声怒吼。");

        // 镜头二：废土龙宫寻器
        shots.add("美猴王踏入一座被黄沙掩埋大半的海底遗迹，这里曾经的龙宫如今破败不堪，巨大的珊瑚礁变成了尖锐的残骸，破碎的水晶在昏暗的光线下闪烁着诡异的光。美猴王在废墟中翻找，惊动了守护这里的变异虾蟹怪。他灵活地躲避着攻击，最终在一个被杂物堆满的角落里，发现了散发着幽光的定海神针，它周围的金属都被腐蚀，唯有它依旧散发着神秘的力量。");

        // 镜头三：废土天庭反叛
        shots.add("曾经辉煌的天庭如今成了一座巨大的垃圾场，破败的宫殿摇摇欲坠，电线和管道杂乱地垂落。美猴王身着破旧却散发着金属光泽的战甲，头戴歪歪斜斜的官帽，他一脚踢翻了御马监的破旧食槽，那些被辐射变异的天马四处乱窜。他扯下官服，跃上一辆由废旧零件拼凑而成的飞行摩托，朝着被烟雾笼罩的南天门疾驰而去，身后是一群手持简陋武器追赶的废土天兵。");

        // 镜头四：废土蟠桃盛宴破坏
        shots.add("蟠桃会的场地变成了一个巨大的垃圾处理场，散发着刺鼻的气味。曾经的仙果变成了干瘪、长满瘤子的变异果实，美酒也变成了散发着恶臭的液体。美猴王大摇大摆地走进来，一脚踢翻了堆满垃圾的桌子，他用金箍棒将那些变异果实和液体四处飞溅，吓得那些穿着破布和金属片的仙女们尖叫着四处逃窜，整个场地陷入一片混乱。");

        // 镜头五：废土激战天庭
        shots.add("南天门下，是一片由生锈的金属和废弃建筑构成的战场。十万废土天兵天将手持自制的武器，有绑着刀片的棍棒、用废旧铁皮做成的盾牌。李靖的玲珑宝塔变成了一个锈迹斑斑的金属塔，哪吒的风火轮也变成了冒着黑烟的机械装置。美猴王站在一座废墟上，手持金箍棒，身上的披风在狂风中猎猎作响。他一声怒吼，冲向天兵天将，金箍棒每一次挥舞都带起一片尘土和废铁，双方在这片废土上展开了一场激烈而混乱的战斗。");


        for (String shot : shots) {
            System.out.println(shot);
            WanVisionConfig config = new WanVisionConfig();
            config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
            config.setModel(WanVisionModelEnums.WANX2_1_T2V_PLUS.getModelName());

            BaseVision<WanVisionConfig> vision = new WanVision(config);

            GenVideoResponse genVideoResponse = vision.textToVideo(shot, null) ;
            System.out.println(genVideoResponse);
        }

        System.in.read();
    }
}
