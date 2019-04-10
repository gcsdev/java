package controllers;

import play.mvc.*;
import models.Detail;
import models.SaleSearch;
import models.SaleSearchData;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import views.html.*;
import java.util.List;
import javax.inject.Inject;
import io.ebean.SqlRow;
import repository.DetailRepository;
import services.Importacao;

import play.data.format.Formats;

import static play.data.Form.*;
import static play.libs.Scala.asScala;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

    private final Importacao importacao;
    private final DetailRepository detailRepository;
    private final FormFactory formFactory;

    @Inject
    public HomeController(Importacao importacao, DetailRepository detailRepository, FormFactory formFactory) {
        this.importacao = importacao;
        this.detailRepository = detailRepository;
        this.formFactory = formFactory;
        // this.form = FormFactory.form(SaleSearch.class);

    }

    /**
     * An action that renders an HTML page with a welcome message. The configuration
     * in the <code>routes</code> file means that this method will be called when
     * the application receives a <code>GET</code> request with a path of
     * <code>/</code>.
     */
    public Result index() {
        Form<Detail> detailForm = formFactory.form(Detail.class);
        List<Detail> details = this.detailRepository.getAll();
        return ok(index.render(details, detailForm));
    }

    public Result dataImport() {
        this.importacao.importSaleFile();
        return redirect(routes.HomeController.index());
    }

    public Result filterByDate() {
        // get form
        Form<Detail> detailFormResp = formFactory.form(Detail.class).bindFromRequest();
        Detail detail = detailFormResp.get();
        String[] split = (detail.getSaleDate()).split("-");
        String year = split[0];
        String month = split[1];
        String day = split[2];
        String date = day + "/" + month + "/" + year;

        List<Detail> details = this.detailRepository.getBySaleData(date);
        Form<Detail> detailForm = formFactory.form(Detail.class);

        return ok(index.render(details, detailForm));
    }

}
