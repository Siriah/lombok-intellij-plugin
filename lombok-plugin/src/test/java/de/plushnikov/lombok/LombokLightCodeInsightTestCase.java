package de.plushnikov.lombok;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import junit.framework.ComparisonFailure;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public abstract class LombokLightCodeInsightTestCase extends LightCodeInsightFixtureTestCase {
  private static final String LOMBOK_SRC_PATH = "./lombok-api/target/generated-sources/lombok";
  private static final String LOMBOKPG_SRC_PATH = "./lombok-api/target/generated-sources/lombok-pg";

  @Override
  protected String getTestDataPath() {
    return ".";
  }

  @Override
  protected String getBasePath() {
    return "lombok-plugin/src/test/data";
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    addLombokClassesToFixture();
  }

  private void addLombokClassesToFixture() throws IOException {
    loadFilesFrom(LOMBOK_SRC_PATH);
    loadFilesFrom(LOMBOKPG_SRC_PATH);
  }

  private void loadFilesFrom(final String srcPath) {
    List<File> filesByMask = FileUtil.findFilesByMask(Pattern.compile(".*\\.java"), new File(srcPath));
    for (File javaFile : filesByMask) {
      String filePath = javaFile.getPath().replace("\\", "/");
      myFixture.copyFileToProject(filePath, filePath.substring(srcPath.length() + 1));
    }
  }

  protected PsiFile loadToPsiFile(String fileName) {
    final String sourceFilePath = getBasePath() + "/" + fileName;
    VirtualFile virtualFile = myFixture.copyFileToProject(sourceFilePath, fileName);
    myFixture.configureFromExistingVirtualFile(virtualFile);
    return myFixture.getFile();
  }

  protected void checkResultByFile(String expectedFile) throws IOException {
    try {
      myFixture.checkResultByFile(expectedFile, true);
    } catch (ComparisonFailure ex) {
      String actualFileText = myFixture.getFile().getText();
      actualFileText = actualFileText.replace("java.lang.", "");

      final String path = getTestDataPath() + "/" + expectedFile;
      String expectedFileText = StringUtil.convertLineSeparators(FileUtil.loadFile(new File(path)));

      assertEquals(expectedFileText.replaceAll("\\s+", ""), actualFileText.replaceAll("\\s+", ""));
    }
  }
}
