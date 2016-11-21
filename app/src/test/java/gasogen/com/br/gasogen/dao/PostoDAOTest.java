package gasogen.com.br.gasogen.dao;

import android.content.Context;

import org.junit.After;
import org.junit.Test;

import gasogen.com.br.gasogen.modelo.Posto;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PostoDAOTest {

    PostoDAO dao;
    Context context;

    public PostoDAOTest(Context context) {
        this.context = context;
    }

    @After
    public void onCreate() {
        if (dao == null) {
            dao = new PostoDAO(this.context);
        }
    }

    @Test
    public void insere(Posto posto) {
        dao.insereOuAtualiza(posto);

        //Pesquisa para ver se esta ok.
        Posto postoCopia = dao.get(posto.getId());

        //Compara os campos
        assertEquals(posto.getNome(), postoCopia.getNome());
        assertEquals(posto.getDescricao(), postoCopia.getDescricao());
        assertEquals(posto.getLatitude(), postoCopia.getLatitude());
        assertEquals(posto.getLongitude(), postoCopia.getLongitude());

        // TODO: CONTINUAR
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}