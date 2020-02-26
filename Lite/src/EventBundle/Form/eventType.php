<?php

namespace EventBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;

class eventType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre',TextType::class,array('attr'=>array('class'=>'form-control')))
            ->add('dateDebutEvent', DateType::class, ['widget' => 'single_text'])
            ->add('dateFEvent', DateType::class, ['widget' => 'single_text'])
            ->add('adresseEvent')
            // ->add('image')
            ->add('image', FileType::class, array('label' => 'Image(JPG)',
                'data_class' => null
            ))
                //, FileType::class, array('label' => 'Image(JPG)'))
            ->add('nbP')
            ->add('typeEvent')
            ->add('description')

        ;
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EventBundle\Entity\Event'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_event';
    }




}
