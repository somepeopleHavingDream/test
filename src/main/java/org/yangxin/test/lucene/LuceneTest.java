package org.yangxin.test.lucene;

import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/11/17 下午8:54
 */
public class LuceneTest {

    public static void main(String[] args) throws IOException, ParseException {
//        test1();
        test2();
    }

    private static void test2() throws IOException, ParseException {
        FSDirectory directory = FSDirectory.open(Paths.get("lucene-index"));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        String text = "拍手机会";
        String field = "title";

        HanLPAnalyzer analyzer = new HanLPAnalyzer();
        QueryParser parser = new QueryParser(field, analyzer);
        Query query = parser.parse(text);
        TopDocs docs = searcher.search(query, 100);
        System.out.println("命中的记录数：" + docs.totalHits);

        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);
            String id = document.get("id");
            String title = document.get("title");
            System.out.println("id -> " + id);
            System.out.println("title -> " + title);
        }
        reader.close();
    }

    /**
     * 构建索引
     *
     * @throws IOException 输入输出异常
     */
    private static void test1() throws IOException {
        Directory directory = FSDirectory.open(new File("lucene-index").toPath());

        HanLPAnalyzer analyzer = new HanLPAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);

        Map<String, String> map = new HashMap<>(4);
        map.put("1", "Xiaomi/小米 小米9 8GB+128GB 全息幻彩紫 移动联通电信全网通4G手机");
        map.put("2", "Xiaomi/小米 小米9 8GB+128GB 全息幻彩蓝 移动联通电信全网通4G手机");
        map.put("3", "Xiaomi/小米 小米9 6GB+128GB 全息幻彩蓝 移动联通电信全网通4G手机");
        map.put("4", "Xiaomi/小米 小米9 6GB+128GB 深空灰 移动联通电信全网通4G手机");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Document document = new Document();
            document.add(new TextField("id", entry.getKey(), Field.Store.YES));
            document.add(new TextField("title", entry.getValue(), Field.Store.YES));

            writer.addDocument(document);
        }
        writer.close();
    }
}
