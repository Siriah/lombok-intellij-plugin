package de.plushnikov.intellij.plugin.processor.method;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import de.plushnikov.intellij.plugin.problem.ProblemBuilder;
import de.plushnikov.intellij.plugin.processor.handler.BuilderHandler;
import de.plushnikov.intellij.plugin.util.PsiClassUtil;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Inspect and validate @Builder lombok annotation on a static method.
 * Creates methods for a builder pattern for initializing a class.
 *
 * @author Tomasz Kalkosiński
 * @author Michail Plushnikov
 */
public class BuilderMethodProcessor extends AbstractMethodProcessor {

  private final BuilderHandler builderHandler = new BuilderHandler();

  public BuilderMethodProcessor() {
    this(Builder.class);
  }

  protected BuilderMethodProcessor(@NotNull Class<? extends Annotation> builderClass) {
    super(builderClass, PsiMethod.class);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiMethod psiMethod, @NotNull ProblemBuilder builder) {
    return builderHandler.validate(psiAnnotation, psiMethod, false, builder);
  }

  protected void processIntern(@NotNull PsiMethod psiMethod, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final PsiClass psiClass = psiMethod.getContainingClass();
    if (null != psiClass) {

      final PsiType psiBuilderType = builderHandler.getBuilderType(psiMethod, psiClass);

      final String builderClassName = builderHandler.getBuilderClassName(psiClass, psiAnnotation, psiBuilderType);
      final PsiClass builderClass = PsiClassUtil.getInnerClassByName(psiClass, builderClassName);
      if (null != builderClass) {
        target.add(builderHandler.createBuilderMethod(psiClass, builderClass, psiAnnotation));
      }
    }
  }
}
