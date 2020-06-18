<?php

namespace AdministrationBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class NoteTypee extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('cc')->add('test')->add('examen')->add('MoyenneGeneral')
            ->add('Matiere', EntityType  ::class, array(
                'class' => 'AdministrationBundle\Entity\Matiere',
                'choice_label' => 'id',
                'multiple' => false))

            ->add('Eleve', EntityType   ::class, array(
                'class' => 'AdministrationBundle\Entity\Eleve',
                'choice_label' => 'id',
                'multiple' => false
            ));
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'AdministrationBundle\Entity\Note'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'administrationbundle_note';
    }


}
