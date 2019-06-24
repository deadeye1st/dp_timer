package com.mrcoder.sbjpamultidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Scheduler {
    @Autowired
    @Qualifier("slaveJdbcTemplate")
    protected JdbcTemplate slaveTempleate;
    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate masterTempleate;

    //每隔5分钟执行一次
//    @Scheduled(fixedRate = 1000*60*5)
//    public void testTasks() {
//
//    }

    //每天3：05执行
//    @Scheduled(cron = "0 50 09 ? * *")
        @Scheduled(fixedRate = 1000*60*60)
    public void taskPerHour() {
        updateDP_EVERY_MONTH_EQUIP_PRODUCT(slaveTempleate,1);
        updateCountry();
    }

    private void updateDP_EVERY_MONTH_EQUIP_PRODUCT(JdbcTemplate template,int plant_id ) {
        List<Map<String, Object>> maps = template.queryForList("SELECT\n" +
                "  DATE_ADD(curdate(),interval -day(curdate())+1 day) AS month,\n" +
                "  IFNULL(M.VOL,SUM(IFNULL(L.volume,T.DwgVol))) AS vols \n" +
                " FROM\n" +
                "  ch1_shipsheet S\n" +
                "  LEFT JOIN ch1_shipmain M ON M.Id = S.Mid\n" +
                "  LEFT JOIN ch1_shipsplit L ON L.ShipId = S.Id \n" +
                "  LEFT JOIN yw1_ordersheet YS ON YS.POrderId = S.POrderId\n" +
                "  LEFT JOIN yw1_ordermain YM ON YM.OrderNumber = YS.OrderNumber\n" +
                "  LEFT JOIN productdata P ON P.ProductId = S.ProductId\n" +
                "  LEFT JOIN trade_drawing T ON P.DrawingId = T.Id\n" +
                "  INNER JOIN trade_object O ON O.Id = T.TradeId\n" +
                "  INNER JOIN companyinfo C ON C.CompanyId = O.CompanyId \n" +
                " WHERE\n" +
                "  S.Type = '1' \n" +
                "  AND M.Estate = '0' \n" +
                "  AND YEAR(S.Date)=YEAR(NOW())\n" +
                "\tand month(s.Date)=month(now())\n" +
                " GROUP BY\n" +
                "DATE_FORMAT( S.Date, '%m' )");
        for (Map<String, Object> map : maps) {
            String month = map.get("month").toString();
            String vols = map.get("vols").toString();
            int update = masterTempleate.update("update DP_EVERY_MONTH_EQUIP_PRODUCT emep set emep.amount=" + vols + "\n" +
                    "where emep.every_month=to_date('" + month + "','yyyy-MM-dd') and emep.plant_id="+plant_id);
            System.out.println("updateDP_EVERY_MONTH_EQUIP_PRODUCT = " + update);
        }
    }

    private void updateCountry() {
        int update = masterTempleate.update("update DP_EVERY_MONTH_EQUIP_PRODUCT emep set emep.amount=\n" +
                "(select sum(amount) from DP_EVERY_MONTH_EQUIP_PRODUCT em where em.every_month=trunc(sysdate, 'mm') and em.plant_id!=0)\n" +
                "where emep.every_month=trunc(sysdate, 'mm') and emep.plant_id=0");
        System.out.println("updateDP_EVERY_MONTH_EQUIP_PRODUCT = " + update);
    }


}