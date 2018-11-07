package JavaParser源码解析工具;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.text.StringSubstitutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * @fileName:QDoxTest
 * @author:xy
 * @date:2018/9/7
 * @description:
 */
public class JavaParserTest {

    private static CompilationUnit compilationUnit;

    /**
     * CompilationUnit 这个暂时可以理解为 要解析的某个类的封装
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        compilationUnit = JavaParser.parse(new File("G:\\work\\data\\idea_workspace\\maven_project\\three\\src\\main\\java\\JavaParser源码解析工具\\Test.java"));
        Optional<PackageDeclaration> packageDeclaration = compilationUnit.getPackageDeclaration();
        System.out.println("获取当前类包："+packageDeclaration.get());
        System.out.println("获取导入的包："+ compilationUnit.getImports());
        System.out.println("获取全类名："+ compilationUnit.getClass());
//        System.out.println(""+compilationUnit.getTypes());
        System.out.println("获取注释"+ compilationUnit.getComments());
        System.out.println(""+ compilationUnit.getMetaModel());
        /** 下面假设有一个需求，项目不允许变量名超过3*/
        VoidVisitorAdapter adapter=new VoidVisitorAdapter() {
            @Override
            public void visit(FieldDeclaration n, Object arg) {
                super.visit(n, arg);
                System.out.println(n.getVariables());
                for (VariableDeclarator v :n.getVariables() ) {
                    if (v.getName().toString().length()>3){
                        System.out.println("变量名不能超过三位!");
                    }
                }
            }
        };
        adapter.visit(compilationUnit, null);
    }
}
