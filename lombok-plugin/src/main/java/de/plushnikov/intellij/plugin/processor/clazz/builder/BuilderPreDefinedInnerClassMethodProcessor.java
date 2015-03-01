package de.plushnikov.intellij.plugin.processor.clazz.builder;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;

/**
 * Creates methods for a builder inner class if it is predefined.
 *
 * @author Michail Plushnikov
 */
public class BuilderPreDefinedInnerClassMethodProcessor extends AbstractBuilderPreDefinedInnerClassProcessor {

  public BuilderPreDefinedInnerClassMethodProcessor() {
    this(Builder.class);
  }

  protected BuilderPreDefinedInnerClassMethodProcessor(@NotNull Class<? extends Annotation> builderClass) {
    super(builderClass, PsiMethod.class);
  }

  protected void generatePsiElements(@NotNull PsiClass psiParentClass, @Nullable PsiMethod psiParentMethod, @NotNull PsiClass psiBuilderClass, @NotNull PsiAnnotation psiAnnotation, @NotNull List<? super PsiElement> target) {
    final PsiType psiBuilderType = builderHandler.getBuilderType(psiParentClass, psiParentMethod);
    if (null == psiParentMethod) {
      final Collection<PsiField> tmpFields = builderHandler.createFields(psiParentClass);
      target.addAll(builderHandler.createConstructors(psiBuilderClass, psiAnnotation));
      target.addAll(builderHandler.createMethods(psiParentClass, null, psiBuilderClass, psiBuilderType, psiAnnotation, tmpFields));
    } else {
      final Collection<PsiField> tmpFields = builderHandler.createFields(psiParentMethod);
      target.addAll(builderHandler.createConstructors(psiBuilderClass, psiAnnotation));
      target.addAll(builderHandler.createMethods(psiParentClass, psiParentMethod, psiBuilderClass, psiBuilderType, psiAnnotation, tmpFields));
    }
  }

}
