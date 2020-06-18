<?php

namespace activiteBundle\Form;

use activiteBundle\Controller\ReponseController;
use activiteBundle\Entity\Questions;
use Doctrine\ORM\Mapping\Entity;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use activiteBundle\Entity\ReponseEleve;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Doctrine\ORM\EntityRepository;
class ReponseEleveType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array                $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        /** @var ReponseEleve $reponseEleve */
        $reponseEleve = $builder->getData();
        $question = new Questions();
        if($reponseEleve != null)
        $question->setQuestion($reponseEleve->getQuestion());

        $builder
            ->add('question'  )
            ->add(
                'reponse',
                EntityType::class,
                [
                    'class' => 'activiteBundle:Reponse',
                    'query_builder' => function (EntityRepository $er) use (
                        $question
                    ) {
                        return $er->createQueryBuilder('reponse')
                            ->where('reponse.question = :que')
                            ->setParameter('que', $question->getId() );
                    },
                    'choice_label' => 'reponse',
                    'expanded' => true ,
                ]


            );
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'activiteBundle\Entity\ReponseEleve'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'activitebundle_reponseeleve';
    }



}
