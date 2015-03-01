package de.plushnikov.intellij.plugin.processor.method;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import de.plushnikov.intellij.plugin.problem.ProblemBuilder;
import de.plushnikov.intellij.plugin.processor.handler.BuilderHandler;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Inspect and validate @Builder lombok annotation on a method
 * Creates inner class for a builder pattern
 *
 * @author Tomasz Kalkosiński
 * @author Michail Plushnikov
 */
public class BuilderClassMethodProcessor extends AbstractMethodProcessor {

  private final BuilderHandler builderHandler = new BuilderHandler();

  public BuilderClassMethodProcessor() {
    this(Builder.class);
  }

  protected BuilderClassMethodProcessor(@NotNull Class<? extends Annotation> builderClass) {
    super(builderClass, PsiClass.class);
  }

  @Override
  protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiMethod psiMethod, @NotNull ProblemBuilder builder) {
    return builderHandler.validate(psiMethod, psiAnnotation, builder);
  }

  protected void processIntern(@NotNull PsiMethod psiMethod, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final PsiClass psiClass = psiMethod.getContainingClass();
    if (null != psiClass) {
      if (builderHandler.existInnerClass(psiClass, psiMethod, psiAnnotation)) {
        target.add(builderHandler.createBuilderClass(psiClass, psiMethod, psiAnnotation));
      }
    }
  }
}
