
$(document).ready(function() {
    $('#table_journal').on('click', '.showJournal', function(e) {
        e.preventDefault();
        var idJournal = $(this).attr('data-id');
        $('#viewJournalModal').modal();
        $.ajax({
            url: 'getJournalProgramme',
            type: 'get',
            data: 'idJournal=' + idJournal,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescriptionJournalProgramme').val(response.description);
                $('#editLibelleJournalProgramme').val(response.libelle);
                $('#editIdJournalHiddenProgramme').val(idJournal);
            }
            , error: function(response) {
                $.notify(response.msg, "error");
            }
        });
    });
    $("#formEditJournalProgrammeButton").click(function(e) {
        var descriptionJournal = $('#editDescriptionJournalProgramme').val();
        var libelleJournal = $('#editLibelleJournalProgramme').val();
        var idJournal = $('#editIdJournalHiddenProgramme').val();
        if (libelleJournal.length < 1) {
              $.notify("Libelle tres court", "error");
            return;
        }
        if (descriptionJournal.length < 1) {
              $.notify("Description tres courte", "error");
            return;
        }
        $.ajax({
            type: 'GET',
            url: 'journal/update',
            timeout: 50000,
            data: {'idJournal': idJournal, 'libelle': libelleJournal, 'description': descriptionJournal},
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                $.notify(response.msg, "error");
            }
        });
    });

    $('#table_journal').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Journaux par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun journal trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    $('#table_etats_activites').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Etats par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun etat trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    $(function() {
        $(".programmeChangerEtatFormButton").click(function(e) {
            var id = $(this).attr('valueIdProgrammeHidden');
            var nom = $(this).attr('valueNomEtatHidden');
            $.ajax({
                type: 'GET',
                url: 'activite/changerEtat',
                timeout: 50000,
                data: {'idActivite': id, 'nomEtat': nom},
                success: function(response) {
                    $.notify(response.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                }
                , error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        });
    });

    $(function() {
        $(".projetChangerEtatFormButton").click(function(e) {
            var id = $(this).attr('valueIdProjetHidden');
            var nom = $(this).attr('valueNomEtatHidden');
            $.ajax({
                type: 'GET',
                url: 'activite/changerEtat',
                timeout: 50000,
                data: {'idActivite': id, 'nomEtat': nom},
                success: function(response) {
                    $.notify(response.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                }
                , error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        });
    });

    $(function() {
        $(".deleteJournalProgrammeById").click(function(e) {
            var id = $(this).attr('data-id');
            $('#deleteJournalConfirmationModal').modal();
            $("#confirmationSuppresionJournalProgramme").click(function(e) {
                deleteJournalByid(id);
            });
        });
    });

    function deleteJournalByid(id) {
        $.ajax({
            type: 'GET',
            url: 'journal/delete',
            data: {'idJournal': id},
            timeout: 50000,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                $.notify(response.msg, "error");
            }
        });
    }
    $(function() {
        $("#btnProgrammeAjouterJournal").click(function(e) {
            var idProgramme = $(this).attr('valueIdProgrammeHidden');
            $('#ajoutJournalProgrammeModal').modal();
            $("#btnValiderAjoutJournalProgramme").click(function(e) {
                var libelle = $("#addLibelleJournalProgramme").val();
                var description = $("#addDescriptionJournalProgramme").val();
                if (libelle.length < 2) {
                    $.notify("Libelle tres court", "error");
                    return;
                }
                if (description.length < 2) {
                    $.notify("Description tres courte", "error");
                    return;
                }
                addJournalByIdProgramme(idProgramme, libelle, description);
            });
        });
    });
    function addJournalByIdProgramme(idProgramme, libelle, description) {
        $.ajax({
            type: 'GET',
            url: 'journal/add',
            data: {'idProgramme': idProgramme, 'libelle': libelle, 'description': description},
            timeout: 50000,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }, 
            error: function(response) {
               $.notify(response.msg, "error");
            }
        });
    }
    
        $('.div-list').on('click','.div-item',function () {
             $('.div-item').removeClass('selected-div');
             $(this).addClass('selected-div')
        });
        
        $('#btnModifierObjetLocalisable').on('click', '.btn', function(e) {
                    //$('#localisationModalForm').modal();
                    showModal('localisationModalForm');
                });
});