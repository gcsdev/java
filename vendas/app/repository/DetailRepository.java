package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import play.data.format.Formats;
import play.db.ebean.EbeanConfig;
import models.Detail;
import javax.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class DetailRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public DetailRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    public Long insert(Detail detail) {
        // return supplyAsync(() -> {
        detail.id = System.currentTimeMillis();
        ebeanServer.insert(detail);
        return detail.id;
        // }, executionContext);
    }

    public List<Detail> getBySaleData(String initialData) {
        // System.out.println("date:" + initialData.getDay() + "/" +
        // initialData.getMonth() + "/" + initialData.getYear());
        List<Detail> details = ebeanServer.find(Detail.class).where().eq("sale_date", initialData).findList();
        return details;
    }

    public List<Detail> getAll() {
        List<Detail> details = ebeanServer.find(Detail.class).findList();
        return details;
    }

}