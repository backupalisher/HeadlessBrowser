import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws IOException {
        // Отключаем отображение ошибок
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);

        // Если есть URL, то запрашиваем снимок страницы (вместе с контентом, генерируемым ajax)
        if (args.length > 0) {
            // Используем движок Firefox 3.6 (он совместим с большинством js-библиотек)
            WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60);
            // Ждем, пока отработают ajax-запросы, выполняемые при загрузке страницы
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            // Запрашиваем и рендерим веб-страницу
            HtmlPage page = webClient.getPage(args[0]);
            // Выводим исходный код страницы в консоль
            System.out.println(page.asXml());
            // Закрываем headless-браузер, освобождаем память
            webClient.close();
        }
    }
}
