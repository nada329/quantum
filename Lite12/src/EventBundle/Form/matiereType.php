<?php

namespace EventBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;

class matiereType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomEvent',TextType::class,array('attr'=>array('class'=>'form-control')))
         
            ->add('dateEvent', DateType::class, ['widget' => 'single_text'])
            ->add('adresseEvent')
            // ->add('image')
            ->add('image', FileType::class, array('label' => 'Image(JPG)',
                'data_class' => null
            ))
                //, FileType::class, array('label' => 'Image(JPG)'))
            ->add('nbP')
              ->add('Prix')
            ->add('typeEvent',ChoiceType::class,array('choices'=>array('Sport'=>'Sport','Divertissements'=>'Divertissements', 'Culturel'=>'Culturel','Excursions'=>'Excursions')))
            ->add('description',TextareaType::class,array('required'=>false))

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
