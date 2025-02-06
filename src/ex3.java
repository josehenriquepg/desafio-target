import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class ex3 {
    public static void main(String[] args) {
        try {
            File arquivoXML = new File("faturamento.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(arquivoXML);
            doc.getDocumentElement().normalize();

            NodeList listaDias = doc.getElementsByTagName("dia");

            double menor = Double.MAX_VALUE, maior = Double.MIN_VALUE, soma = 0;
            int diasValidos = 0, diasAcimaMedia = 0;

            for (int i = 0; i < listaDias.getLength(); i++) {
                Element elemento = (Element) listaDias.item(i);
                double valor = Double.parseDouble(elemento.getAttribute("valor"));

                if (valor > 0) {
                    if (valor < menor) menor = valor;
                    if (valor > maior) maior = valor;
                    soma += valor;
                    diasValidos++;
                }
            }

            double media = soma / diasValidos;

            for (int i = 0; i < listaDias.getLength(); i++) {
                Element elemento = (Element) listaDias.item(i);
                double valor = Double.parseDouble(elemento.getAttribute("valor"));
                if (valor > media) diasAcimaMedia++;
            }

            System.out.println("Menor faturamento: " + menor);
            System.out.println("Maior faturamento: " + maior);
            System.out.println("Dias com faturamento acima da média: " + diasAcimaMedia);

        } catch (Exception e) {
            System.err.println("Erro ao processar o XML: " + e.getMessage());
        }
    }
}
