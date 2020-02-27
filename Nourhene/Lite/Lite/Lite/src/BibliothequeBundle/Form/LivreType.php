<?php

namespace BibliothequeBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use BibliothequeBundle\Entity\Categorie;

class LivreType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom',TextType::class)
            ->add('quantity',NumberType::class)
            ->add('resume',TextareaType::class)
            ->add('photo',FileType::class,array('data_class'=>null ,
            'label' => false))
            ->add('auteur',TextType::class)
            ->add('nbDePages',NumberType::class)
            ->add('editur',TextType::class)
            ->add('category',EntityType::class, [
            // looks for choices from this entity
            'class' => Categorie::class,
            'choice_label' => 'nom',
            'required'=>false
            // 'multiple' => true,
            // 'expanded' => true,
        ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'BibliothequeBundle\Entity\Livre'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'userbundle_livre';
    }


}
